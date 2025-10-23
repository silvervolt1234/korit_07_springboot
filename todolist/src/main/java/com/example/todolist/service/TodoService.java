package com.example.todolist.service;

import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo updateTodoStatus(Long id) {
        Optional<Todo> todoOpt = todoRepository.findById(id);

        todoOpt.ifPresent(todo -> {
            todo.setCompleted(!todo.isCompleted());
            todoRepository.save(todo);
        });
        return null;
    }

    public void clearCompletedTodos(Long id) {
        List<Todo> completedTodos = todoRepository.findByUserIdAndIsCompletedTrue(id);
        todoRepository.deleteAll(completedTodos);
    }
}

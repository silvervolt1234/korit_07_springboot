package com.example.todolist.web;

import com.example.todolist.domain.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> toggleTodoStatus(@PathVariable Long id) {
        // updateTodoStatus 메서드에서 토글 + 저장까지 처리하고
        Todo updatedTodo = todoService.updateTodoStatus(id);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/completed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompletedTodos(@RequestParam Long userId) {
        todoService.clearCompletedTodos(userId);
    }
}

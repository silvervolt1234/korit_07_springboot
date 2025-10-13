package com.todolist2.demo.web;

import com.todolist2.demo.domain.Todo;
import com.todolist2.demo.dto.TodoRequestDto;
import com.todolist2.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.getTodosForCurrentUser());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoRequestDto requestDto) {
        Todo createdTodo = todoService.createTodo(requestDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    // 수정 / 삭제 파트에 해당하는 Controller 단계의 메서드 정의
    // 수정 / 삭제 파트에서 DB에 있는 데이터를 수정 / 삭제하는 로직은 TodoService에 정의
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto updateDto) throws AccessDeniedException {
        return ResponseEntity.ok(todoService.updateTodoContent(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) throws AccessDeniedException{
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    // 토글 관련 메서드 정의
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggleTodoStatus(@PathVariable Long id) throws AccessDeniedException {
        return ResponseEntity.ok(todoService.toggleTodoStatus(id));
    }

    // 일괄 삭제 메서드 정의
    @DeleteMapping("/completed")
    public ResponseEntity<Void> clearCompletedTodos() {
        todoService.clearCompletedTodos();
        return ResponseEntity.noContent().build();
    }
}

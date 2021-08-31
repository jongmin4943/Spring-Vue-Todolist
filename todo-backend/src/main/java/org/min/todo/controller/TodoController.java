package org.min.todo.controller;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.PageDto;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;
import org.min.todo.security.principal.PrincipalDetails;
import org.min.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/")
    public ResponseEntity insertTodo(@RequestBody TodoDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        dto.setUsername(principalDetails.getUsername());
        todoService.register(dto);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto dto) {
        todoService.modify(dto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/")
    public ResponseEntity<TodoListDto> getTodos(PageDto pageDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(todoService.getAllTodos(pageDto, principalDetails.getUsername()));
    }
    
    @GetMapping("/{tno}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long tno) {
        return ResponseEntity.ok(todoService.getTodo(tno));
    }

    @DeleteMapping("/")
    public ResponseEntity<String> removeTodo(@RequestBody Map<String,List<Long>> tnoData) {
        return ResponseEntity.ok(todoService.remove(tnoData.get("tnos")));
    }

}

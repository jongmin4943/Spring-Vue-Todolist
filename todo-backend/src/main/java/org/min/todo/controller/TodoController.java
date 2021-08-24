package org.min.todo.controller;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.PageDto;
import org.min.todo.dto.TodoDto;
import org.min.todo.dto.TodoListDto;
import org.min.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @PostMapping("/")
    public ResponseEntity<TodoDto> insertTodo(@RequestBody TodoDto dto) {
        return ResponseEntity.ok(todoService.register(dto));
    }

    @PutMapping("/")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto dto) {
        return ResponseEntity.ok(todoService.modify(dto));
    }

    @GetMapping("/")
    public ResponseEntity<TodoListDto> getTodos(PageDto pageDto) {
        return ResponseEntity.ok(todoService.getAllTodos(pageDto));
    }

    @GetMapping("/{tno}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long tno) {
        return ResponseEntity.ok(todoService.getTodo(tno));
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDto> doneTodo(@PathVariable Long tno) {
        return ResponseEntity.ok(todoService.complete(tno));
    }

    @DeleteMapping("/")
    public ResponseEntity<String> removeTodo(@RequestBody Map<String,List<Long>> tnoData) {
        return ResponseEntity.ok(todoService.remove(tnoData.get("tnos")));
    }

}

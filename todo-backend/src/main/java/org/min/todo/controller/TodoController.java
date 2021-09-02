package org.min.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long tno) {
        todoService.complete(tno);
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

    @PutMapping("/change/position/{target}")
    public ResponseEntity changePositionTodo(@PathVariable Long target, @RequestBody TodoDto dto) {
        todoService.changePosition(dto,target);
        return ResponseEntity.ok(null);
    }
    @PutMapping("/change/position/done/{target}")
    public ResponseEntity changePositionAndCompleteTodo(@PathVariable Long target, @RequestBody TodoDto dto) {
        todoService.changePositionAndCompleteTodo(dto,target);
        return ResponseEntity.ok(null);
    }

}

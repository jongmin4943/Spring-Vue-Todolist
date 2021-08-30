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

    /**
     * @Method Name : insertTodo
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 새로운 todo 를 저장한다.
     * @param dto 저장 될 todo 의 정보
     * @param principalDetails 요청한 대상
     * @return 저장된 todo의 정보
     */
    @PostMapping("/")
    public ResponseEntity<TodoDto> insertTodo(@RequestBody TodoDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        dto.setUsername(principalDetails.getUsername());
        return ResponseEntity.ok(todoService.register(dto));
    }

    /**
     * @Method Name : updateTodo
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : todo의 내용을 수정한다.
     * @param dto 수정 할 todo의 정보
     * @return 수정 된 todo의 정보
     */
    @PutMapping("/")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto dto) {
        return ResponseEntity.ok(todoService.modify(dto));
    }

    /**
     * @Method Name : getTodos
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 요청 대상의 todo 리스트를 반환한다.
     * @param pageDto todo 리스트의 페이지 정보 keyword, page, size를 지정 할 수 있다.
     * @param principalDetails 요청 대상
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<TodoListDto> getTodos(PageDto pageDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(todoService.getAllTodos(pageDto, principalDetails.getUsername()));
    }
    
    @GetMapping("/{tno}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long tno) {
        return ResponseEntity.ok(todoService.getTodo(tno));
    }

    /**
     * @Method Name : doneTodo
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 요청된 todo의 완료 상태를 바꾼다.
     * @param tno 요청 할 todo의 ID
     * @return 변경된 todo 정보
     */
    @PutMapping("/{tno}")
    public ResponseEntity<TodoDto> doneTodo(@PathVariable Long tno) {
        return ResponseEntity.ok(todoService.complete(tno));
    }

    /**
     * @Method Name : removeTodo
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 : 
     * @Method 설명 : todo 들을 삭제 시킨다.
     * @param tnoData todo의 ID들이 담긴 List를 value로 하고 tnos라는 key에 담겨있는 Map
     * @return 삭제 여부
     */
    @DeleteMapping("/")
    public ResponseEntity<String> removeTodo(@RequestBody Map<String,List<Long>> tnoData) {
        return ResponseEntity.ok(todoService.remove(tnoData.get("tnos")));
    }

}

package org.min.todo.service;

import org.min.todo.dto.*;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;
import org.min.todo.entity.Todo;
import org.min.todo.entity.User;

import java.util.List;

public interface TodoService {

    TodoDto register(TodoDto dto);

    TodoDto modify(TodoDto dto);

    /**
     * @Method Name : getAllTodos
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : page정보와 요청 대상을 이용해 DB를 조회하여 나온 값을 TodoListDto에 넣어준다.
     * @param pageDto
     * @param username
     * @return TodoListDto
     */
    TodoListDto getAllTodos(PageDto pageDto, String username);

    TodoDto getTodo(Long tno);

    String remove(List<Long> tnos);

    /**
     * @Method Name : complete
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : todo의 완료 상태를 바꾼다.
     * @param tno
     * @return 완료 상태가 바뀐 todo
     */
    TodoDto complete(Long tno);


    default TodoDto entityToDTO(Todo todo) {
        return TodoDto.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .done(todo.isDone())
                .createdDate(todo.getCreateDate())
                .updatedDate(todo.getUpdatedDate())
                .build();
    }
    default Todo dtoToEntity(TodoDto dto) {
        return Todo.builder()
                .tno(dto.getTno())
                .user(User.builder().username(dto.getUsername()).build())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }

    /**
     * @Method Name : arrToDTO
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : todo와 user객체가 담긴 Object[]을 받은 뒤 dto로 변환 시킨다.
     * @param arr 0번지는 Todo 1번지에는 User 객체가 담긴 Object[]
     * @return TodoDto
     */
    default TodoDto arrToDTO(Object[] arr) {
        Todo todo = (Todo) arr[0];
        User user = (User) arr[1];

        return TodoDto.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .done(todo.isDone())
                .username(user.getUsername())
                .createdDate(todo.getCreateDate())
                .updatedDate(todo.getUpdatedDate())
                .build();

    }
}

package org.min.todo.service;

import org.min.todo.dto.*;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;
import org.min.todo.dto.todo.TodoUserDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.entity.Todo;
import org.min.todo.entity.User;

import java.util.List;

public interface TodoService {

    TodoDto register(TodoDto dto);

    TodoDto modify(TodoDto dto);

    TodoListDto getAllTodos(PageDto pageDto, String username);

    TodoDto getTodo(Long tno);

    String remove(List<Long> tnos);

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
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
    default TodoUserDto arrToDTO(Object[] arr) {
        Todo todo = (Todo) arr[0];
        User user = (User) arr[1];
        UserDto userDto= UserDto
                .builder()
                .username(user.getUsername())
                .createdDate(user.getCreateDate())
                .updatedDate(user.getUpdatedDate())
                .build();

        return TodoUserDto.builder()
                .todoDto(entityToDTO(todo))
                .userDto(userDto)
                .build();
    }
}

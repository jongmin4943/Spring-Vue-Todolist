package org.min.todo.service;

import org.min.todo.dto.PageDto;
import org.min.todo.dto.TodoDto;
import org.min.todo.entity.Todo;
import org.springframework.data.domain.Page;

public interface TodoService {

    TodoDto register(TodoDto dto);

    TodoDto modify(TodoDto dto);

    Page<TodoDto> getAllTodos(PageDto pageDto);

    TodoDto getTodo(Long tno);

    String remove(Long tno);

    TodoDto complete(Long tno);


    default TodoDto entityToDTO(Todo todo) {
        return TodoDto.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .done(todo.isDone())
                .deleted(todo.isDeleted())
                .createdDate(todo.getCreateDate())
                .updatedDate(todo.getUpdatedDate())
                .build();
    }
    default Todo dtoToEntity(TodoDto dto) {
        return Todo.builder()
                .tno(dto.getTno())
                .title(dto.getTitle())
                .done(dto.isDone())
                .deleted(dto.isDeleted())
                .build();
    }
}

package org.min.todo.service;

import org.min.todo.dto.PageDto;
import org.min.todo.dto.TodoDto;
import org.min.todo.dto.TodoListDto;
import org.min.todo.entity.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {

    TodoDto register(TodoDto dto);

    TodoDto modify(TodoDto dto);

    TodoListDto getAllTodos(PageDto pageDto);

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
}

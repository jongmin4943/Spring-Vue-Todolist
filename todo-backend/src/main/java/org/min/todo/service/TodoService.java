package org.min.todo.service;

import org.min.todo.dto.*;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;

import java.util.List;

public interface TodoService {

    void register(TodoDto dto);

    void modify(TodoDto dto);

    void complete(Long tno);

    TodoListDto getAllTodos(PageDto pageDto, String username);

    TodoDto getTodo(Long tno);

    String remove(List<Long> tnos);

    void changePosition(TodoDto dto,long target);

    void changePositionAndCompleteTodo(TodoDto dto, long target);
}

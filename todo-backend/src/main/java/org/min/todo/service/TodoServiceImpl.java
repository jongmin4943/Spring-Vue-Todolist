package org.min.todo.service;

import org.min.todo.dto.PageDto;
import org.min.todo.dto.TodoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService{
    @Override
    public TodoDto register(TodoDto dto) {
        return null;
    }

    @Override
    public TodoDto modify(TodoDto dto) {
        return null;
    }

    @Override
    public Page<TodoDto> getAllTodos(PageDto pageDto) {
        return null;
    }

    @Override
    public TodoDto getTodo(Long tno) {
        return null;
    }

    @Override
    public String remove(Long tno) {
        return null;
    }

    @Override
    public TodoDto complete(Long tno) {
        return null;
    }
}

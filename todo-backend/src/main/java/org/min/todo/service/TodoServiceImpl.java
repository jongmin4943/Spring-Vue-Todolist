package org.min.todo.service;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.*;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;
import org.min.todo.dto.todo.TodoUserDto;
import org.min.todo.entity.Todo;
import org.min.todo.repository.TodoRepository;
import org.min.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto register(TodoDto dto) {
        Todo newTodo = todoRepository.save(dtoToEntity(dto));
        return entityToDTO(newTodo);
    }

    @Override
    public TodoDto modify(TodoDto dto) {
        Todo oldTodo = todoRepository.findById(dto.getTno()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 Todo입니다."));
        oldTodo.modify(dto);
        Todo newTodo = todoRepository.save(oldTodo);
        return entityToDTO(newTodo);
    }

    @Override
    public TodoListDto getAllTodos(PageDto pageDto, String username) {
        Page<Object[]> result = todoRepository.getSearchedListWithUser(pageDto.getKeyword(),pageDto.getPageable(), username);
        List<TodoUserDto> todoList = result.stream().map(arr->arrToDTO(arr)).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(pageDto.getPage(), pageDto.getSize(), (int) result.getTotalElements(), pageDto.getKeyword());
        return TodoListDto.builder().todoList(todoList).pageInfo(pageInfo).build();
    }

    @Override
    public TodoDto getTodo(Long tno) {
        Todo todo = todoRepository.findById(tno).orElseThrow(()->new IllegalArgumentException("존재하지 않는 Todo입니다."));
        return entityToDTO(todo);
    }

    @Override
    public String remove(List<Long> tnos) {
        tnos.forEach(tno -> {
            todoRepository.deleteById(tno);
        });
        return "Success";
    }

    @Override
    public TodoDto complete(Long tno) {
        Todo oldTodo = todoRepository.findById(tno).orElseThrow(()->new IllegalArgumentException("존재하지 않는 Todo입니다."));
        oldTodo.done();
        Todo newTodo = todoRepository.save(oldTodo);
        return entityToDTO(newTodo);
    }
}

package org.min.todo.service;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.PageDto;
import org.min.todo.dto.PageInfo;
import org.min.todo.dto.TodoDto;
import org.min.todo.dto.TodoListDto;
import org.min.todo.entity.Todo;
import org.min.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

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
    public TodoListDto getAllTodos(PageDto pageDto) {
        Page<Todo> todos = todoRepository.getSearchedList(pageDto.getKeyword(),pageDto.getPageable());
        List<TodoDto> todoList = todos.stream().map(todo->entityToDTO(todo)).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(pageDto.getPage(), pageDto.getSize(), (int) todos.getTotalElements(), pageDto.getKeyword());
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

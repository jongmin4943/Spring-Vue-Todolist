package org.min.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.min.todo.dto.*;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;
import org.min.todo.repository.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    @Override
    public void register(TodoDto dto) {
        int result = todoMapper.save(dto);
        if(result <= 0) throw new IllegalArgumentException("작성에 실패 했습니다.");
    }

    @Override
    public void modify(TodoDto dto) {
//        TodoDto todo = todoMapper.findById(dto.getTno());
//        if(todo == null) new IllegalArgumentException("존재하지 않는 Todo입니다.");
        int result = todoMapper.modify(dto);
        if(result <= 0) throw new IllegalArgumentException("수정에 실패 했습니다.");
    }

    @Override
    public TodoListDto getAllTodos(PageDto pageDto, String username) {
        List<TodoDto> result = todoMapper.getSearchedListWithUser(pageDto.getKeyword(),pageDto.getPage(),pageDto.getSize(), username);
        long totalCount = todoMapper.countAll(pageDto.getKeyword(),username);
        PageInfo pageInfo = new PageInfo(pageDto.getPage(), pageDto.getSize(), (int) totalCount, pageDto.getKeyword());
        return TodoListDto.builder().todoList(result).pageInfo(pageInfo).build();
    }

    @Override
    public TodoDto getTodo(Long tno) {
        TodoDto todo = todoMapper.findById(tno);
        if(todo == null) throw new IllegalArgumentException("존재하지 않는 todo 입니다.");
        return todo;
    }

    @Override
    public String remove(List<Long> tnos) {
        int result = todoMapper.delete(tnos);
        return result > 0 ? "Success" : "Fail";
    }

}

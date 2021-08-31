package org.min.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.min.todo.dto.todo.TodoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TodoMapper {
    int save(TodoDto dto);

    int modify(TodoDto dto);

    List<TodoDto> getSearchedListWithUser(String keyword, int page, int size, String username);

    long countAll(String keyword, String username);

    TodoDto findById(Long tno);

    int delete(List<Long> tnos);
}

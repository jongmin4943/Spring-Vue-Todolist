package org.min.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.min.todo.dto.PageDto;
import org.min.todo.dto.todo.TodoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TodoMapper {
    int save(TodoDto dto);

    int modify(TodoDto dto);

    int done(Long tno);

    List<TodoDto> getSearchedListWithUser(@Param("pageDto") PageDto pageDto, @Param("username") String username);

    long countAll(@Param("pageDto") PageDto pageDto, @Param("username") String username);

    TodoDto findById(Long tno);

    int delete(List<Long> tnos);
}


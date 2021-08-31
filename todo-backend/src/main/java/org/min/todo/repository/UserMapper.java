package org.min.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.min.todo.dto.user.UserDto;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {

    UserDto findByUsername(String username);

    int deleteById(String username);

    int save(UserDto userDto);

    int modify(UserDto dto);
}

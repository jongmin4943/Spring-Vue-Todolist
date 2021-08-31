package org.min.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.min.todo.dto.user.UserDto;
import org.min.todo.dto.user.UserRole;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {

    UserDto findByUsername(String username);

    int deleteById(String username);

    int save(UserDto userDto);

    int setRole(UserRole userRole);

    int modify(UserDto dto);
}

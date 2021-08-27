package org.min.todo.service;

import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.entity.User;


public interface UserService {
    UserDto register(UserDto dto);

    UserDto modify(UserDto dto);

    UserDto getUser(String username);

    String remove(String username);

    TokenDto login(UserDto dto);

    default UserDto entityToDTO(User user) {
        return UserDto
                .builder()
                .username(user.getUsername())
                .createdDate(user.getCreateDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    default User dtoToEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .build();
    }


}

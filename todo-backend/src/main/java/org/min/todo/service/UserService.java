package org.min.todo.service;

import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;


public interface UserService {

    void register(UserDto dto);

    void modify(UserDto dto);

    UserDto getUser(String username);

    String remove(String username);

    TokenDto login(UserDto dto);

    TokenDto refreshToken(TokenDto dto);


}

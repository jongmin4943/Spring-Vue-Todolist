package org.min.todo.service;

import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.security.jwt.JwtTokenProvider;

import java.util.List;
import java.util.stream.Collectors;


public interface UserService {

    void register(UserDto dto);

    void modify(UserDto dto);

    UserDto getUser(String username);

    String remove(String username);

    TokenDto login(UserDto dto);

    TokenDto refreshToken(TokenDto dto);

    /**
     * @Method Name : generateTokenDto
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 유저 객체를 토대로 TokenDto를 생성 해 반환한다.
     * @param user
     * @param jwtTokenProvider
     * @return ToKenDto
     */
    default TokenDto generateTokenDto(UserDto user, JwtTokenProvider jwtTokenProvider) {
        List<String> roles = user.getRoles().stream().map(role->role.getRole()).collect(Collectors.toList());
        String accessToken = jwtTokenProvider.doGenerateToken(user.getUsername(),roles);
        return TokenDto.builder()
                .accessToken(accessToken)
                .username(user.getUsername())
                .build();
    }

}

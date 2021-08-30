package org.min.todo.service;

import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.entity.User;


public interface UserService {

    /**
     * @Method Name : register
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 가입하려는 아이디가 이미 존재하면 exception을 throw 하고 아니면 그 정보를 패스워드 encode와 기본 권한정보를 넣어 db에 저장한다.
     * @param dto
     * @return 저장된 user정보
     */
    UserDto register(UserDto dto);



    UserDto modify(UserDto dto);

    UserDto getUser(String username);

    String remove(String username);

    /**
     * @Method Name : login
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 들어온 username과 password를 이용하여 username이 존재하고 password가 일치하면 jwt토큰을 발행한다.
     * @param dto
     * @return accessToken과 유저 정보
     */
    TokenDto login(UserDto dto);

    /**
     * @Method Name : refreshToken
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 들어온 토큰을 검사해 정상적인 토큰이면 accessToken을 재 발행 해준다.
     * @param dto
     * @return accessToken과 유저 정보
     */
    TokenDto refreshToken(TokenDto dto);

    default UserDto entityToDTO(User user) {
        return UserDto
                .builder()
                .username(user.getUsername())
                .roleSet(user.getUserRoleSet())
                .createdDate(user.getCreateDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    default User dtoToEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .userRoleSet(dto.getRoleSet())
                .build();
    }



}

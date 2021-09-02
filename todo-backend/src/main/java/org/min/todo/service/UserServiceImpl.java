package org.min.todo.service;

import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.dto.user.UserRole;
import org.min.todo.exception.UserNotFoundException;
import org.min.todo.exception.UsernameDuplicateException;
import org.min.todo.repository.UserMapper;
import org.min.todo.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void register(UserDto dto) {
        UserDto user = userMapper.findByUsername(dto.getUsername());
        if(user != null) throw new UsernameDuplicateException("이미 존재하는 아이디 입니다.");
        dto.setPassword(encoder.encode(dto.getPassword()));
        int result = userMapper.save(dto);
        if(result <= 0) throw new IllegalArgumentException("등록에 실패 했습니다.");
        int roleResult = userMapper.setRole(UserRole.builder().username(dto.getUsername()).role("USER").build());
        if(roleResult <= 0) throw new IllegalArgumentException("권한 부여에 실패 했습니다.");
    }

    @Override
    public TokenDto login(UserDto dto) {
        UserDto user = userMapper.findByUsername(dto.getUsername());
        if(user == null) throw new UserNotFoundException(dto.getUsername() + "은 존재하지 않는 유저입니다.");
        boolean isPasswordCorrect = encoder.matches(dto.getPassword(),user.getPassword());
        if(isPasswordCorrect) {
           return generateTokenDto(user,jwtTokenProvider);
        }
        throw new BadCredentialsException("비밀번호가 틀렸습니다.");
    }

    @Override
    public TokenDto refreshToken(TokenDto dto) {
        if(jwtTokenProvider.validateToken(dto.getAccessToken())){
            UserDto user = userMapper.findByUsername(dto.getUsername());
            if(user == null) throw new UserNotFoundException(dto.getUsername() + "은 존재하지 않는 유저입니다.");
            return generateTokenDto(user,jwtTokenProvider);
        };
        throw new UnsupportedJwtException("유효하지 않는 토큰입니다.");
    }

    @Override
    public void modify(UserDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        int result = userMapper.modify(dto);
        if(result <= 0) throw new IllegalArgumentException("수정을 실패 했습니다.");
    }

    @Override
    public UserDto getUser(String username) {
        UserDto user = userMapper.findByUsername(username);
        if(user == null) throw new UserNotFoundException(username + "는 존재하지 않는 유저 입니다.");
        return user;
    }

    @Override
    @Transactional
    public String remove(String username) {
        int roleResult = userMapper.deleteRole(username);
        if(roleResult <= 0) throw new IllegalArgumentException("권한 제거를 실패 했습니다.");
        int result = userMapper.deleteById(username);
        if(result <= 0) throw new IllegalArgumentException("유저 삭제를 실패 했습니다.");
        return "Success";
    }


}

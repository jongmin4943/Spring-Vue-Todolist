package org.min.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.dto.user.UserRole;
import org.min.todo.repository.UserMapper;
import org.min.todo.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;

    @Override
    public void register(UserDto dto) {
        UserDto user = userMapper.findByUsername(dto.getUsername());
        if(user != null) throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        dto.setPassword(encoder.encode(dto.getPassword()));
        int result = userMapper.save(dto);
        int roleResult = userMapper.setRole(UserRole.builder().username(dto.getUsername()).role("USER").build());
        if(result <= 0 && roleResult <= 0) throw new IllegalArgumentException("가입을 실패 했습니다.");
    }

    @Override
    public TokenDto login(UserDto dto) {
        UserDto user = userMapper.findByUsername(dto.getUsername());
        if(user == null) throw new IllegalArgumentException(dto.getUsername() + "은 존재하지 않는 유저입니다.");
        boolean isPasswordCorrect = encoder.matches(dto.getPassword(),user.getPassword());
        if(isPasswordCorrect) {
            List<String> roles = user.getRoles().stream().map(role->role.getRole()).collect(Collectors.toList());
            String accessToken = jwtTokenProvider.doGenerateToken(user.getUsername(),roles);
            return TokenDto.builder()
                    .accessToken(accessToken)
                    .username(dto.getUsername())
                    .build();
        }
        throw new BadCredentialsException("비밀번호가 틀렸습니다.");
    }

    @Override
    public TokenDto refreshToken(TokenDto dto) {
        if(jwtTokenProvider.validateToken(dto.getAccessToken())){
            UserDto user = userMapper.findByUsername(dto.getUsername());
            if(user == null) throw new IllegalArgumentException(dto.getUsername() + "은 존재하지 않는 유저입니다.");
            List<String> roles = user.getRoles().stream().map(role->role.getRole()).collect(Collectors.toList());
            String accessToken = jwtTokenProvider.doGenerateToken(user.getUsername(),roles);
            return TokenDto.builder()
                    .accessToken(accessToken)
                    .username(dto.getUsername())
                    .build();
        };
        throw new IllegalArgumentException("잘못된 요청입니다.");
    }

    @Override
    public void modify(UserDto dto) {
//        UserDto user = userMapper.findByUsername(dto.getUsername());
//        if(user == null) throw new IllegalArgumentException(dto.getUsername() + "은 존재하지 않는 유저입니다.");
        dto.setPassword(encoder.encode(dto.getPassword()));
        int result = userMapper.modify(dto);
        if(result <= 0) throw new IllegalArgumentException("수정을 실패 했습니다..");
    }

    @Override
    public UserDto getUser(String username) {
        UserDto user = userMapper.findByUsername(username);
        if(user == null) throw new IllegalArgumentException(username + "는 존재하지 않는 유저 입니다.");
        return user;
    }

    @Override
    public String remove(String username) {
        int result = userMapper.deleteById(username);
        return result > 0 ? "Success" : "Fail";
    }


}

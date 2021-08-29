package org.min.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.entity.User;
import org.min.todo.entity.UserRole;
import org.min.todo.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;

    @Override
    public UserDto register(UserDto dto) {
        boolean result = userRepository.findById(dto.getUsername()).isPresent();
        if(result) throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        dto.setPassword(encoder.encode(dto.getPassword()));
        dto.addRole(UserRole.USER);
        User user = userRepository.save(dtoToEntity(dto));
        return entityToDTO(user);
    }

    @Override
    public TokenDto login(UserDto dto) {
        User user = userRepository.findById(dto.getUsername()).orElseThrow(()->new IllegalArgumentException(dto.getUsername() + "은 존재하지 않는 유저입니다."));
        boolean isPasswordCorrect = encoder.matches(dto.getPassword(),user.getPassword());
        if(isPasswordCorrect) {
            List<String> roles = user.getUserRoleSet().stream().map(role->role.name()).collect(Collectors.toList());
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
            User user = userRepository.findById(dto.getUsername()).orElseThrow(()->new IllegalArgumentException(dto.getUsername() + "은 존재하지 않는 유저입니다."));
            List<String> roles = user.getUserRoleSet().stream().map(role->role.name()).collect(Collectors.toList());
            String accessToken = jwtTokenProvider.doGenerateToken(user.getUsername(),roles);
            return TokenDto.builder()
                    .accessToken(accessToken)
                    .username(dto.getUsername())
                    .build();
        };
        throw new IllegalArgumentException("잘못된 요청입니다.");
    }

    @Override
    public UserDto modify(UserDto dto) {
        User oldUser = userRepository.findById(dto.getUsername()).orElseThrow(()->new IllegalArgumentException(dto.getUsername() + "은 존재하지 않는 유저입니다."));
        oldUser.changePassword(encoder.encode(dto.getPassword()));
        User newUser = userRepository.save(oldUser);
        return entityToDTO(newUser);
    }

    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findById(username).orElseThrow(()->new IllegalArgumentException(username + "은 존재하지 않는 유저입니다."));
        return entityToDTO(user);
    }

    @Override
    public String remove(String username) {
        userRepository.deleteById(username);
        return "Success";
    }


}

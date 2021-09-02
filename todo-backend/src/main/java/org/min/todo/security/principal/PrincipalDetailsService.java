package org.min.todo.security.principal;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.user.UserDto;
import org.min.todo.exception.UserNotFoundException;
import org.min.todo.repository.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto user = userMapper.findByUsername(username);
        if(user == null) throw new UserNotFoundException("존재하지 않는 유저입니다.");
        return new PrincipalDetails(user);
    }

}

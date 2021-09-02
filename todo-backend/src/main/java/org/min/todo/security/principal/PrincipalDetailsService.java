package org.min.todo.security.principal;

import lombok.RequiredArgsConstructor;
import org.min.todo.entity.User;
import org.min.todo.exception.UserNotFoundException;
import org.min.todo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("존재하지 않는 유저입니다."));
        return new PrincipalDetails(user);
    }

}

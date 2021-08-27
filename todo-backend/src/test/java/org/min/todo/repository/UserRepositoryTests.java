package org.min.todo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.min.todo.entity.User;
import org.min.todo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testUserRepo () {
        log.info(userRepository);
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Set<UserRole> roles = new HashSet<>();
            roles.add(UserRole.USER);
            User user = User.builder()
                    .username("test"+i)
                    .password(encoder.encode("1111"))
                    .userRoleSet(roles)
                    .build();
            userRepository.save(user);
        });
    }

    @Test
    public void testGet() {
        Page<User> result = userRepository.findAll(PageRequest.of(0,10));
        result.forEach(user -> {
            log.info(user);
        });
    }

    @Test
    public void testDelete() {
        userRepository.deleteById("test1");
    }

}

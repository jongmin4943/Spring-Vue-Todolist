package org.min.todo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.min.todo.dto.user.UserDto;
import org.min.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;


@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testGet() {
        UserDto dto = userService.getUser("test1");
//        log.warn(encoder.matches("2222", dto.getPassword()));
        log.info(dto);
    }

    @Test
    public void testSave() {

        IntStream.rangeClosed(1,100).forEach(i->{
            userService.register(UserDto.builder().username("test"+i).password("1111").build());
        });

//        userService.register(UserDto.builder().username("test1").password(encoder.encode("1111")).build());
    }

    @Test
    public void testUpdate() {
        UserDto dto = UserDto.builder().username("test2").password("1111").build();
        userService.modify(dto);
    }

    @Test
    public void testDelete() {
        userService.remove("test1");
    }

}

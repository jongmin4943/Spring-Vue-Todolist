package org.min.todo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class TodoRepositoryTests {
    @Autowired
    TodoService todoService;

    @Test
    public void testGet() {
        TodoDto dto = todoService.getTodo(1L);
        log.info(dto);
    }

}

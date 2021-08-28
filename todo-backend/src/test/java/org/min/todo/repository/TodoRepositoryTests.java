package org.min.todo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.entity.Todo;
import org.min.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class TodoRepositoryTests {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void repositoryTest() {
        log.info(todoRepository);
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            User user = User.builder()
                    .username("test"+i)
                    .build();
            Todo todo = Todo.builder()
                    .title("test todo " + i)
                    .user(user)
                    .build();
            todoRepository.save(todo);
        });
    }

    @Test
    public void testGet() {
        Page<Object[]> result = todoRepository.getSearchedListWithUser("", PageRequest.of(0,10),"test2");

        log.info(result.getTotalElements());
        result.forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }

    @Test
    public void testUpdate() {
        Todo todo = todoRepository.findById(2L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 todo"));
        todo.modify(TodoDto.builder().title("updated Todo").build());
        todoRepository.save(todo);
    }

    @Test
    public void testDelete() {
        todoRepository.deleteById(2L);
    }
}

package org.min.todo.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.min.todo.dto.PageDto;
import org.min.todo.dto.todo.TodoDto;
import org.min.todo.dto.todo.TodoListDto;
import org.min.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.IntStream;

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

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            TodoDto dto = TodoDto.builder().username("test2").title("test todo"+i).build();
            todoService.register(dto);
        });
    }

    @Test
    public void testUpdate() {
        todoService.modify(TodoDto.builder().tno(1L).username("test2").title("updated todo2").build());
    }

    @Test
    public void testDelete() {
        todoService.remove(Arrays.asList(3L,4L,5L));
    }

    @Test
    public void testGetAllTodos(){
        PageDto dto = PageDto.builder().page(2).size(10).keyword("1").build();
        TodoListDto result = todoService.getAllTodos(dto,"test2");
        result.getTodoList().forEach(todo->{
            log.info(todo);
        });
        log.info(result.getPageInfo());
    }

}

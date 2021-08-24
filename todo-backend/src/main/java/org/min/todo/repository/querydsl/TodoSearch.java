package org.min.todo.repository.querydsl;

import org.min.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {

    Page<Todo> getSearchedList(String keyword, Pageable pageable);

}

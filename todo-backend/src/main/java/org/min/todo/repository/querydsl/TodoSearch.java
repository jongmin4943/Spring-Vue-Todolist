package org.min.todo.repository.querydsl;

import org.min.todo.entity.Todo;
import org.min.todo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {

    Page<Object[]> getSearchedList(String keyword, Pageable pageable);
    Page<Object[]> getSearchedListWithUser(String keyword, Pageable pageable, String username);

}

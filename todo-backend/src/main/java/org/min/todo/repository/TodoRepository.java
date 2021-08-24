package org.min.todo.repository;

import org.min.todo.entity.Todo;
import org.min.todo.repository.querydsl.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long>, TodoSearch {
}

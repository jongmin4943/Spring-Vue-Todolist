package org.min.todo.repository.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.min.todo.entity.QTodo;
import org.min.todo.entity.QUser;
import org.min.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Object[]> getSearchedList(String keyword, Pageable pageable) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        JPQLQuery<Todo> query = from(todo);
        query.leftJoin(user).on(todo.user.eq(user));
        JPQLQuery<Tuple> tuple = query.select(todo,user);
        if(keyword != null && keyword != "") {
            tuple.where(todo.title.contains(keyword));
        }

        tuple.where(todo.tno.gt(0L));
        tuple.orderBy(todo.tno.desc());
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> list=tuple.fetch();
        long count = tuple.fetchCount();

        return new PageImpl<>(
                list.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }


    @Override
    public Page<Object[]> getSearchedListWithUser(String keyword, Pageable pageable, String username) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        JPQLQuery<Todo> query = from(todo);
        query.leftJoin(user).on(todo.user.eq(user));

        JPQLQuery<Tuple> tuple = query.select(todo,user);
        if(keyword != null && keyword != "") {
            tuple.where(todo.title.contains(keyword));
        }

        tuple.where(user.username.eq(username));
        tuple.orderBy(todo.tno.desc());
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> list=tuple.fetch();
        long count = tuple.fetchCount();

        return new PageImpl<>(
                list.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}

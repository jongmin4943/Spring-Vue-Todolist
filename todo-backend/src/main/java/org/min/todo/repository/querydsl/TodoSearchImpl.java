package org.min.todo.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.min.todo.entity.QTodo;
import org.min.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {
    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> getSearchedList(String keyword, Pageable pageable) {
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        if(keyword != null && keyword != "") {
            query.where(todo.title.contains(keyword));
        }

        query.where(todo.tno.gt(0L));
        query.orderBy(todo.tno.desc());
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> list=query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }
}

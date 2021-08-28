package org.min.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.min.todo.dto.PageInfo;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDto {
    private List<TodoDto> todoList;
    private PageInfo pageInfo;
}

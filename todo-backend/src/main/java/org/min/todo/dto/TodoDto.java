package org.min.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    private Long tno;

    private String title;

    private boolean done;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}

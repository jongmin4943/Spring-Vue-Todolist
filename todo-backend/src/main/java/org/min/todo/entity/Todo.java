package org.min.todo.entity;
import lombok.*;
import org.min.todo.dto.todo.TodoDto;

import javax.persistence.*;

@Entity
@Table(name="tbl_todo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user"})
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length = 255,nullable = false)
    private String title;

    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void modify(TodoDto dto) {
        this.title = dto.getTitle();
    }

    public void done() {
        this.done = !this.done;
    }

}

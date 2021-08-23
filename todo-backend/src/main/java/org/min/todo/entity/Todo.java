package org.min.todo.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_todo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String title;

    private boolean done;

    private boolean deleted;
}

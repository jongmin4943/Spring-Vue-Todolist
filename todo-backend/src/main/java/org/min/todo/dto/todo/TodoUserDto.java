package org.min.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.min.todo.dto.user.UserDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoUserDto {
    private TodoDto todoDto;
    private UserDto userDto;
}

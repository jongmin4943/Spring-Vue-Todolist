package org.min.todo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;

    private String password;

    @Builder.Default
    private List<UserRole> roles = new ArrayList<>();

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}

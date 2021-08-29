package org.min.todo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.min.todo.entity.UserRole;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;

    private String password;

    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public void addRole(UserRole role) {
        roleSet.add(role);
    }
}

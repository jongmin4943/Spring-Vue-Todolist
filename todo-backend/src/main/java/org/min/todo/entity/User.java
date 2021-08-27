package org.min.todo.entity;

import lombok.*;
import org.min.todo.dto.user.UserDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userRoleSet"})
public class User extends BaseEntity{
    @Id
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> userRoleSet = new HashSet<>();

    public void addMemberRole(UserRole role){
        userRoleSet.add(role);
    }
    public void changePassword(String password) { this.password = password;}
}

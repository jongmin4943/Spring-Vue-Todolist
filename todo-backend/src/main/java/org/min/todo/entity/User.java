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
    @Column(length = 50)
    private String username;

    @Column(length = 50,nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> userRoleSet = new HashSet<>();

    public void addMemberRole(UserRole role){
        userRoleSet.add(role);
    }
    public void changePassword(String password) { this.password = password;}
}

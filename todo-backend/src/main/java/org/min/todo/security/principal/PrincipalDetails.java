package org.min.todo.security.principal;

import lombok.Getter;
import org.min.todo.dto.user.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PrincipalDetails implements UserDetails {

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authority;

    public PrincipalDetails(UserDto user) {
        List<GrantedAuthority> authority =
                user.getRoles()
                        .stream()
                        .map((role)->new SimpleGrantedAuthority("ROLE_"+role))
                        .collect(Collectors.toList());

        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authority = authority;
    }
    public PrincipalDetails(String username,Collection<GrantedAuthority> authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

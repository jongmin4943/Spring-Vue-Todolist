package org.min.todo.controller;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.security.principal.PrincipalDetails;
import org.min.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> insertUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUsername().equals(dto.getUsername())) {
            return ResponseEntity.ok(userService.modify(dto));
        }
        throw new IllegalArgumentException("권한이 없습니다.");
    }

    @GetMapping("/get/user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> removeUser(@PathVariable String username, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUsername().equals(username)) {
            return ResponseEntity.ok(userService.remove(username));
        }
        throw new IllegalArgumentException("권한이 없습니다.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshToken(@RequestBody TokenDto dto) {
        return ResponseEntity.ok(userService.refreshToken(dto));
    }
}

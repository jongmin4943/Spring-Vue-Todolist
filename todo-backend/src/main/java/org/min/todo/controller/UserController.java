package org.min.todo.controller;

import lombok.RequiredArgsConstructor;
import org.min.todo.dto.user.TokenDto;
import org.min.todo.dto.user.UserDto;
import org.min.todo.exception.ForbiddenException;
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
    public ResponseEntity insertUser(@RequestBody UserDto dto) {
        userService.register(dto);

        return ResponseEntity.ok("Success");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    /**
     * @Method Name : updateUser
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : Security Context Holder 안에 있는 유저정보와 dto의 유저 정보가 같으면 유저 정보를 수정한다.
     * @param dto 변경하는 대상과 내용
     * @param principalDetails 요청한 대상
     * @return 변경된 유저 정보
     */
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserDto dto,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.modify(dto);
        if(principalDetails.getUsername().equals(dto.getUsername())) return ResponseEntity.ok(null);
        throw new ForbiddenException("권한이 없습니다.");
    }

    /**
     * @Method Name : getUser
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 선택한 유저의 정보를 가져온다.
     * @param username 선택할 유저의 아이디
     * @return 가져온 유저 정보
     */
    @GetMapping("/get/user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    /**
     * @Method Name : updateUser
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : Security Context Holder 안에 있는 유저정보와 삭제하려는 유저의 정보가 같으면 유저 정보를 삭제한다.
     * @param username 삭제하려는 유저의 아이디
     * @param principalDetails 요청한 대상
     * @return 삭제여부
     */
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> removeUser(@PathVariable String username, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails.getUsername().equals(username)) return ResponseEntity.ok(userService.remove(username));
        throw new ForbiddenException("권한이 없습니다.");
    }

    /**
     * @Method Name : refreshToken
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : accessToken을 새로 발급해준다.
     * @param dto accessToken 정보와 유저 아이디
     * @return 새로 발급한 accessToken과 유저 아이디
     */
    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refreshToken(@RequestBody TokenDto dto) {
        return ResponseEntity.ok(userService.refreshToken(dto));
    }
}

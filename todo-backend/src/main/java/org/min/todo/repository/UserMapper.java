package org.min.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.min.todo.dto.user.UserDto;
import org.min.todo.dto.user.UserRole;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {

    /**
     * @Method Name : findByUsername
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : username으로 user를 db에서 권한과 함께 조회한다.
     * @param username
     * @return 권한 정보가 포함 된 UserDto
     */
    UserDto findByUsername(String username);

    /**
     * @Method Name : deleteById
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : username을 기준으로 user를 삭제한다.
     * @param username
     * @return 삭제 된 row의 개수
     */
    int deleteById(String username);

    /**
     * @Method Name : deleteRole
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : username을 기준으로 user_role을 삭제한다.
     * @param username
     * @return 삭제 된 row의 개수
     */
    int deleteRole(String username);

    /**
     * @Method Name : save
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 유저 정보를 저장한다.
     * @param userDto
     * @return 저장된 row의 개수
     */
    int save(UserDto userDto);

    /**
     * @Method Name : setRole
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 유저의 권한을 저장한다.
     * @param userRole USER
     * @return 저장된 row의 개수
     */
    int setRole(UserRole userRole);

    /**
     * @Method Name : modify
     * @작성일 : 2021. 09. 01.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 유저의 정보를 수정한다.
     * @param dto 수정하고싶은 유저의 정보
     * @return 수정된 row의 개수
     */
    int modify(UserDto dto);
}

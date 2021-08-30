package org.min.todo.repository;

import org.min.todo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    /**
     * @Method Name : findByUsername
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 유저의 권한 테이블과 함께 조회한다.
     * @param username user의 ID
     * @return 조회 된 user 객체
     */
    @EntityGraph(attributePaths = "userRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from User u where u.username = :username ")
    Optional<User> findByUsername(String username);

    /**
     * @Method Name : findAll
     * @작성일 : 2021. 08. 30.
     * @작성자 : J.M YOON
     * @변경이력 :
     * @Method 설명 : 유저들의 정보를 권한 테이블과 함께 조회한다.
     * @param pageable 가져올 유저들의 페이징처리를 위한 객체
     * @return 조회 된 유저 리스트
     */
    @EntityGraph(attributePaths = "userRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    Page<User> findAll(Pageable pageable);
}

package org.min.todo.repository;

import org.min.todo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @EntityGraph(attributePaths = "userRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from User u where u.username = :username ")
    Optional<User> findByUsername(String username);

    @EntityGraph(attributePaths = "userRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    Page<User> findAll(Pageable pageable);
}

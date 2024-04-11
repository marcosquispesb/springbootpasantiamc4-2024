package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * AuthUserRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    List<AuthUser> findAllByDeletedFalse();

    Optional<AuthUser> findByIdAndDeletedFalse(Long id);

    @Query("    SELECT d " +
            "   FROM AuthUser d " +
            "   WHERE d.deleted = FALSE " +
            "   AND d.name = :name")
    AuthUser findByName(@Param("name") String name);

    @Query("    SELECT d " +
            "   FROM AuthUser d " +
            "   WHERE d.deleted = FALSE " +
            "   AND d.name = :name")
    Optional<AuthUser> getByName(@Param("name") String name);

    @Query("    SELECT d " +
            "   FROM AuthUser d " +
            "   WHERE d.deleted = FALSE " +
            "   AND d.username = :username")
    AuthUser findByUsername(@Param("username") String username);
}

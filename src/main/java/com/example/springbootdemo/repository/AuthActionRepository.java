package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * AuthActionRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthActionRepository extends JpaRepository<AuthAction, Long> {

    AuthAction findByCode(String code);

    @Query("    SELECT d " +
            "   FROM AuthAction d " +
            "   WHERE d.code = :code")
    Optional<AuthAction> getByCode(@Param("code") String code);

}

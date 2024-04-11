package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthRoleRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthRoleUserRepository extends JpaRepository<AuthRoleUser, Long> {

}

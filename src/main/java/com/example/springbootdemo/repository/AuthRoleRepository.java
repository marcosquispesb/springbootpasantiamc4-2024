package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthRoleRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {

}

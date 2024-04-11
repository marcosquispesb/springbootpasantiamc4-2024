package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthPrivilegeRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthPrivilegeRepository extends JpaRepository<AuthPrivilege, Long> {

}

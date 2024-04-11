package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * AuthResourceRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthResourceRepository extends JpaRepository<AuthResource, Long> {

    List<AuthResource> findAllByDeletedFalse();
}

package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthResource;
import com.example.springbootdemo.model.AuthRoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * AuthRoleResourceRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthRoleResourceRepository extends JpaRepository<AuthRoleResource, Long> {

    @Query("  SELECT a.authResource " +
            " FROM AuthRoleResource a" +
            " WHERE a.authRole.id = :roleId AND a.deleted = false")
    List<AuthResource> findResourcesDtosByRoleId(@Param("roleId") Long roleId);

    @Query("  SELECT a " +
            " FROM AuthRoleResource a" +
            " WHERE a.authRole.id = :roleId AND a.deleted = false")
    List<AuthRoleResource> findResourcesDtosByRoleId2(@Param("roleId") Long roleId);
}

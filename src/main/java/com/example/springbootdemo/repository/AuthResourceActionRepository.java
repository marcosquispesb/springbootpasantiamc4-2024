package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.AuthAction;
import com.example.springbootdemo.model.AuthResourceAction;
import com.example.springbootdemo.rest.dto.AuthActionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * AuthResourceActionRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface AuthResourceActionRepository extends JpaRepository<AuthResourceAction, Long> {

    List<AuthResourceAction> findByAuthResource_IdAndDeletedFalse(Long resourceId);

    @Query("  SELECT a.authAction " +
            " FROM AuthResourceAction a" +
            " WHERE a.authResource.id = :resourceId AND a.deleted = false")
    List<AuthAction> findByResourceId(@Param("resourceId") Long resourceId);

    @Query("  SELECT new com.example.springbootdemo.rest.dto.AuthActionDto(a.authAction) " +
            " FROM AuthResourceAction a" +
            " WHERE a.authResource.id = :resourceId AND a.deleted = false")
    List<AuthActionDto> findDtosByResourceId(@Param("resourceId") Long resourceId);
}

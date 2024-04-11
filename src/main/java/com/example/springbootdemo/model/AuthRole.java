package com.example.springbootdemo.model;

import com.example.springbootdemo.enums.EntityState;
import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AuthRole
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Entity
@Table(name = "AUTH_ROLE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthRole extends AuditableEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @Column(name = "ROLE_STATUS", nullable = false)
//    private String roleStatus;

    @Column(name = "ROLE_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityState roleStatus;
}

package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AuthRoleResource
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTH_ROLE_RESOURCE")
public class AuthRoleResource extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_AUTH_ROLE_RES_ID_GENERATOR", sequenceName = "SEQ_AUTH_ROLE_RESOURCE_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTH_ROLE_RES_ID_GENERATOR")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_ROLE", nullable = false)
    private AuthRole authRole;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_RESOURCE", nullable = false)
    private AuthResource authResource;
}

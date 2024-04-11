package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AuthPrivilege
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
@Table(name = "AUTH_PRIVILEGE")
public class AuthPrivilege extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_AUTH_PRIVILEGE_ID_GENERATOR", sequenceName = "SEQ_AUTH_PRIVILEGE_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTH_PRIVILEGE_ID_GENERATOR")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_ACTION", nullable = false)
    private AuthAction authAction;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_ROLE_RESOURCE", nullable = false)
    private AuthRoleResource authRoleResource;
}

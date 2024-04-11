package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AuthResourceAction
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
@Table(name = "AUTH_RESOURCE_ACTION")
public class AuthResourceAction extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_AUTH_RES_ACT_ID_GENERATOR", sequenceName = "SEQ_AUTH_RES_ACT_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTH_RES_ACT_ID_GENERATOR")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_ACTION", nullable = false)
    private AuthAction authAction;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_RESOURCE", nullable = false)
    private AuthResource authResource;
}

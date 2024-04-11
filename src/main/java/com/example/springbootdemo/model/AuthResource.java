package com.example.springbootdemo.model;

import com.example.springbootdemo.enums.EntityState;
import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * AuthResource
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTH_RESOURCE")
public class AuthResource extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @Column(name = "url")
    private String url;

    @Column(name = "menu_position")
    private Integer menuPosition;

    @Column(name = "RESOURCE_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityState resourceStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_auth_recurso_padre", referencedColumnName = "id")
    private AuthResource recursoPadre;

//    @OneToMany(mappedBy = "authResource", fetch = FetchType.LAZY)
//    private List<AuthResourceAction> resourceActionList;

    @Transient
    private List<AuthAction> actions;
}

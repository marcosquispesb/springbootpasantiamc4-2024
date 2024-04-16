package com.example.springbootdemo.model;

import com.example.springbootdemo.enums.EntityState;
import com.example.springbootdemo.enums.UserType;
import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AuthUser
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Entity
@Table(name = "AUTH_USER")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends AuditableEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_AUTH_USER_ID_GENERATOR", sequenceName = "SEQ_AUTH_USER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTH_USER_ID_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "lastname", length = 20)
    private String lastname;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "generated_password")
    private boolean generatedPassword;

    @Column(name = "USER_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityState userStatus;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_USER", referencedColumnName = "id")
    private AuthUser userJis;

}

package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * AuthAction
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTH_ACTION")
public class AuthAction extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, length = 30)
    private String code;

    @Column(name = "action", nullable = false, length = 30)
    private String action;
}

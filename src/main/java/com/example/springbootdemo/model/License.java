package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * License
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Entity
@Table(name = "LICENSE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class License extends AuditableEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_LICENSE_ID_GENERATOR", sequenceName = "SEQ_LICENSE_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LICENSE_ID_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "description", length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_USER", referencedColumnName = "id", nullable = false)
    private AuthUser user;

}

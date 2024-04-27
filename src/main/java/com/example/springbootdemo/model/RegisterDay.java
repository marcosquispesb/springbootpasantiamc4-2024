package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * RegisterDay
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Entity
@Table(name = "REGISTER_DAY")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDay extends AuditableEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_REGISTER_DAY_ID_GENERATOR", sequenceName = "SEQ_REGISTER_DAY_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REGISTER_DAY_ID_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AUTH_USER", referencedColumnName = "id", nullable = false)
    private AuthUser user;

}

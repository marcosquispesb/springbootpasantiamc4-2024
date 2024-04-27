package com.example.springbootdemo.model;

import com.example.springbootdemo.model.base.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Holiday
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Entity
@Table(name = "HOLIDAY")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Holiday extends AuditableEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_HOLIDAY_ID_GENERATOR", sequenceName = "SEQ_HOLIDAY_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HOLIDAY_ID_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "description", length = 200)
    private String description;

}

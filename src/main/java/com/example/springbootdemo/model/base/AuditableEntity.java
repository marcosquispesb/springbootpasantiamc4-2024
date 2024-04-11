package com.example.springbootdemo.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AuditableEntity implements Serializable {

    @CreatedDate
    protected Date createdDate;

    @CreatedBy
    protected String createdBy;

    @LastModifiedDate
    protected Date modifiedDate;

    @LastModifiedBy
    protected String modifiedBy;

    @Version
    protected Long version;

    protected boolean deleted = false;

}

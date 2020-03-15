package com.ccifuentes.beerservice.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@JsonIgnoreProperties({ "createdBy", "modifiedBy", "createdDate", "modifiedDate" })
public abstract class AuditEntity extends BaseEntity {

    @NotNull
    @Column(nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    @NotNull
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 5259026471640134045L;
}


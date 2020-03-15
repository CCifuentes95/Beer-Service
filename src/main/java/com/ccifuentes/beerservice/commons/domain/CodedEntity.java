package com.ccifuentes.beerservice.commons.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class CodedEntity extends AuditEntity {

    @NotEmpty
    @NotNull
    private String code;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 7140716025695470946L;
}

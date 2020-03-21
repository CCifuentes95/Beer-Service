package com.ccifuentes.beerservice.domain;

import com.ccifuentes.beerservice.commons.domain.AuditEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beer extends AuditEntity {

    @Version
    private Long version;

    @NotNull
    private String beerName;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private BeerStyle beerStyle;

    @Column(unique = true)
    private String upc;

    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    @NotNull
    private Integer minOnHand;

    @Positive
    @NotNull
    @Min(0)
    private Integer quantityToBrew;

    @Builder
    public Beer(UUID id, Long version, String beerName, BeerStyle beerStyle, String upc, BigDecimal price, Integer minOnHand, Integer quantityToBrew,
                String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.setId(id);
        this.version = version;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.price = price;
        this.minOnHand = minOnHand;
        this.quantityToBrew = quantityToBrew;
        this.setCreatedBy(createdBy);
        this.setCreatedDate(createdDate);
        this.setModifiedBy(modifiedBy);
        this.setModifiedDate(modifiedDate);
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -554795700733325818L;
}

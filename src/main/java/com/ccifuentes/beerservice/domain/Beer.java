package com.ccifuentes.beerservice.domain;

import com.ccifuentes.beerservice.commons.domain.AuditEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;
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

    private String beerName;
    private String beerStyle;

    @Column(unique = true)
    private String upc;

    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityToBrew;

    @Builder
    public Beer(UUID id, Long version, String beerName, String beerStyle, String upc, BigDecimal price, Integer minOnHand, Integer quantityToBrew,
                String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.setId(id);
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

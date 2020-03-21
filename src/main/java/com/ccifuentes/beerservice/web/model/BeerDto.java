package com.ccifuentes.beerservice.web.model;

import com.ccifuentes.beerservice.domain.BeerStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"createdDate", "modifiedDate"})
public class BeerDto {
    @Nullable
    private UUID id;

    @Nullable
    private Long version;

    @NotNull
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @Positive
    @NotNull
    private Long upc;

    @Positive
    @NotNull
    private BigDecimal price;

    private Integer minOnHand;

    @Positive
    @NotNull
    @Min(0)
    private Integer quantityToBrew;

    @Nullable
    private LocalDateTime createdDate;

    @Nullable
    private LocalDateTime modifiedDate;

}

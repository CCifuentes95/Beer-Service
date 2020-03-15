package com.ccifuentes.beerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id;
    private String name;
    private BeerStyle beerStyle;
    private Long upc;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}

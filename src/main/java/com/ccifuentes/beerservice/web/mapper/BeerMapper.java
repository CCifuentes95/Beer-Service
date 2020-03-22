package com.ccifuentes.beerservice.web.mapper;

import com.ccifuentes.beerservice.domain.Beer;
import com.ccifuentes.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDto toDto(Beer beer);
    Beer toBeer(BeerDto beerDto);
}

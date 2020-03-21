package com.ccifuentes.beerservice.service;

import com.ccifuentes.beerservice.domain.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<Beer> findAll();

    Optional<Beer> findById(UUID id);

    Beer save(Beer beer);

    void deleteById(UUID id);

}

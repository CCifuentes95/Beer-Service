package com.ccifuentes.beerservice.repository;

import com.ccifuentes.beerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}

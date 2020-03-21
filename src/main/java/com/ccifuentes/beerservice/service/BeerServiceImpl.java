package com.ccifuentes.beerservice.service;

import com.ccifuentes.beerservice.domain.Beer;
import com.ccifuentes.beerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    @Override
    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    @Override
    public Optional<Beer> findById(UUID id) {
        return beerRepository.findById(id);
    }

    @Override
    public Beer save(Beer beer) {
        return beerRepository.save(beer);
    }

    @Override
    public void deleteById(UUID id) {
        beerRepository.deleteById(id);
    }
}

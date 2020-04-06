package com.ccifuentes.beerservice.service;

import com.ccifuentes.beerservice.domain.Beer;
import com.ccifuentes.beerservice.repository.BeerRepository;
import com.ccifuentes.beerservice.web.mapper.BeerMapper;
import com.ccifuentes.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
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
    public List<BeerDto> findAllDto() {
        return this.findAll().stream().map(this.beerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDto> findByIdDto(UUID id) {
        return this.findById(id).map(beerMapper::toDto);
    }

    @Override
    public BeerDto save(BeerDto beerDto) {
        return beerMapper.toDto(this.save(beerMapper.toBeer(beerDto)));
    }

    @Override
    public void deleteById(UUID id) {
        beerRepository.deleteById(id);
    }
}

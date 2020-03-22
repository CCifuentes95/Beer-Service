package com.ccifuentes.beerservice.web.controller;

import com.ccifuentes.beerservice.service.BeerService;
import com.ccifuentes.beerservice.web.mapper.BeerMapper;
import com.ccifuentes.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class  BeerController {

    private final BeerMapper beerMapper;
    private final BeerService beerService;

    @GetMapping({"/",""})
    public ResponseEntity<?> index() {
        var beers = beerService.findAll().stream().map(beerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(beers, HttpStatus.OK);
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<?> findById(@PathVariable UUID beerId) {
        Optional<BeerDto> response = beerService.findById(beerId).map(beerMapper::toDto);
        if(response.isPresent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping({"/",""})
    public ResponseEntity<?> create(@Valid @RequestBody BeerDto beerDto) {
        var beer = beerMapper.toDto(beerService.save(beerMapper.toBeer(beerDto)));
        return new ResponseEntity<>(beer, HttpStatus.CREATED);
    }

    @PutMapping({"/",""})
    public ResponseEntity<?> update(@Valid @RequestBody BeerDto beerDto) {
        var beer = beerMapper.toDto(beerService.save(beerMapper.toBeer(beerDto)));
        return new ResponseEntity<>(beer, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }
}

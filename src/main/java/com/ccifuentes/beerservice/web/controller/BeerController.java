package com.ccifuentes.beerservice.web.controller;

import com.ccifuentes.beerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {


    @GetMapping({"/{beerId}"})
    public ResponseEntity<?> index(@PathVariable UUID beerId) {
        return new ResponseEntity<>(BeerDto.builder().id(beerId).build(), HttpStatus.OK);
    }

    @PostMapping({"/"})
    public ResponseEntity<?> create(@RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(BeerDto.builder().id(beerDto.getId()).build(), HttpStatus.CREATED);
    }

    @PutMapping({"/"})
    public ResponseEntity<?> update(@RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(BeerDto.builder().id(beerDto.getId()).build(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID beerId) {

    }
}

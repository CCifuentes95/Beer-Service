package com.ccifuentes.beerservice.web.controller;

import com.ccifuentes.beerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class    BeerController {


    @GetMapping({"/",""})
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<?> findById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(BeerDto.builder().id(beerId).build(), HttpStatus.OK);
    }

    @PostMapping({"/",""})
    public ResponseEntity<?> create(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(BeerDto.builder().id(beerDto.getId()).build(), HttpStatus.CREATED);
    }

    @PutMapping({"/",""})
    public ResponseEntity<?> update(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(BeerDto.builder().id(beerDto.getId()).build(), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID beerId) {

    }
}

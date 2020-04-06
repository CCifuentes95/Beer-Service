package com.ccifuentes.beerservice.web.controller;

import com.ccifuentes.beerservice.service.BeerService;
import com.ccifuentes.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class  BeerController {

    private final BeerService beerService;

    @GetMapping({"/",""})
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(beerService.findAllDto(), HttpStatus.OK);
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<?> findById(@PathVariable UUID beerId) {
        Optional<BeerDto> response = beerService.findByIdDto(beerId);
        if(response.isPresent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping({"/",""})
    public ResponseEntity<?> create(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.save(beerDto), HttpStatus.CREATED);
    }

    @PutMapping({"/",""})
    public ResponseEntity<?> update(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.save(beerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }
}

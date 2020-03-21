package com.ccifuentes.beerservice.web.controller;

import com.ccifuentes.beerservice.utils.BaseMvcTest;
import com.ccifuentes.beerservice.web.model.BeerDto;
import com.ccifuentes.beerservice.domain.BeerStyle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest extends BaseMvcTest {

    BeerDto validBeer;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Corona")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc(12345L)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .build();
    }

    @Test
    @SneakyThrows
    void index_getCorrectStatus() {
        mockMvc.perform(get("/api/v1/beer/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void findById_getCorrectStatus() {
        assert validBeer.getId() != null;
        mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void create_getCorrectStatus() {

        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void update_getCorrectStatus() {

        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void delete_getCorrectStatus() {
        assert validBeer.getId() != null;
        mockMvc.perform(delete("/api/v1/beer/" + validBeer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
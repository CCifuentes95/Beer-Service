package com.ccifuentes.beerservice.web.controller;

import com.ccifuentes.beerservice.domain.Beer;
import com.ccifuentes.beerservice.domain.BeerStyle;
import com.ccifuentes.beerservice.service.BeerService;
import com.ccifuentes.beerservice.utils.BaseMvcTest;
import com.ccifuentes.beerservice.utils.ConstrainedFields;
import com.ccifuentes.beerservice.web.model.BeerDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.ccifuentes.beerservice.web.mapper")
class BeerControllerTest extends BaseMvcTest {

    BeerDto validBeer;
    String beerDtoJson;

    @MockBean
    BeerService beerService;

    ConstrainedFields fields = new ConstrainedFields(BeerDto.class);


    @BeforeEach
    @SneakyThrows
    void setUp() {
        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Corona")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc(12345L)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .build();
        beerDtoJson = objectMapper.writeValueAsString(validBeer);
    }

    @Test
    @SneakyThrows
    void index_getCorrectStatus() {
        mockMvc.perform(get("/api/v1/beer/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    @SneakyThrows
    void findById_getCorrectStatus() {
        when(beerService.findById(any())).thenReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("v1/beer/",
                                pathParameters(
                                        parameterWithName("beerId").description("UUID of the desired beer to get")
                                ),
                                responseFields(
                                        fields.withPath("id").description("Id of Beer"),
                                        fields.withPath("version").description("Version number"),
                                        fields.withPath("beerName").description("Beer Name"),
                                        fields.withPath("beerStyle").description("Beer Style"),
                                        fields.withPath("upc").description("UPC of Beer"),
                                        fields.withPath("price").description("Price"),
                                        fields.withPath("minOnHand").description("min On hand"),
                                        fields.withPath("quantityToBrew").description("Quantity On hand")

                                )
                        )
                );
    }

    @Test
    @SneakyThrows
    void create_getCorrectStatus() {
        validBeer.setId(null);
        beerDtoJson = objectMapper.writeValueAsString(validBeer);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(
                        document("v1/beer/",
                                requestFields(
                                        fields.withPath("id").ignored(),
                                        fields.withPath("version").ignored(),
                                        fields.withPath("beerName").description("Name of the beer"),
                                        fields.withPath("beerStyle").description("Style of Beer"),
                                        fields.withPath("upc").description("Beer UPC").attributes(),
                                        fields.withPath("price").description("Beer Price"),
                                        fields.withPath("minOnHand").ignored(),
                                        fields.withPath("quantityToBrew").ignored()

                                )
                        )
                );
    }

    @Test
    @SneakyThrows
    void update_getCorrectStatus() {
        mockMvc.perform(put("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void delete_getCorrectStatus() {
        mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
package ru.taheoport.geocalculator_service.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.service.PotenotService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PotenotTaskControllerTest {

    @Autowired
    private PotenotService potenotService;

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @CsvSource({
            "100000, 100000, 904445, 200000, 200000, 1265914, 100000, 300000, 404065, 137114, 209238",
            "100000, 100000, 1195127, 200000, 200000, 62348, 100000, 300000, 224986, 18515, 143314",
            "100000, 100000, 1142287, 200000, 200000, 1168488, 100000, 300000, 1173462, -709005, 846436",
            "-100000, -100000, 587159, -200000, -200000, 662082, -100000, -300000, 742845, 150295, -176044"
    })
    void solvePotenotProblemTest(
            long firstX,
            long firstY,
            long firstDirection,
            long secondX,
            long secondY,
            long secondDirection,
            long thirdX,
            long thirdY,
            long thirdDirection,
            long expectX,
            long expectY
    ) {
        List<PotenotTaskRequest> potenotTaskRequestList = List.of(
                new PotenotTaskRequest(firstX, firstY, firstDirection),
                new PotenotTaskRequest(secondX, secondY, secondDirection),
                new PotenotTaskRequest(thirdX, thirdY, thirdDirection)
        );

        PotenotTaskResponse responseBody = webTestClient.post()
                .uri("direct")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.just(potenotTaskRequestList), PotenotTaskRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PotenotTaskResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
    }
}
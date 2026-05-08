package ru.taheoport.geocalculator_service.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.service.PotenotService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PotenotControllerTest {

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
    void solvePotenotTaskTest(
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
                .uri("potenot")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(potenotTaskRequestList), PotenotTaskRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PotenotTaskResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(expectX, responseBody.getPointX());
        assertEquals(expectY, responseBody.getPointY());
    }

    @Test
    void solvePotenotTaskTestBadRequest() {
        List<PotenotTaskRequest> potenotTaskRequestList = List.of(
                new PotenotTaskRequest(),
                new PotenotTaskRequest()
        );

        webTestClient.post()
                .uri("potenot")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(potenotTaskRequestList), PotenotTaskRequest.class)
                .exchange()
                .expectStatus().isBadRequest();

    }

    @ParameterizedTest
    @CsvSource({
            "100.000, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425, 137.114, 209.238, 'OK'",
            "100.000, 100.000, 331.5847, 200.000, 200.000, 17.1908, 100.000, 300.000, 62.2946, 18.515, 143.314, 'OK'",
            "100.000, 100.000, 317.1807, 200.000, 200.000, 324.3448, 100.000, 300.000, 325.5742, -709.005, 846.436, 'OK'",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, -100.000, -300.000, 206.2045, 150.295, -176.044, 'OK'",
            "bad.000, 100.000, 317.1807, 200.000, 200.000, 324.3448, 100.000, 300.000, 325.5742, 0.000, 0.000, 'Invalid X of point 1!'",
    })
    void getPotenotStringResponseTest(
            String firstX,
            String firstY,
            String firstDirection,
            String secondX,
            String secondY,
            String secondDirection,
            String thirdX,
            String thirdY,
            String thirdDirection,
            String expectX,
            String expectY,
            String expectHeader
    ) {
        List<PotenotStringRequest> potenotStringRequestList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection)
        );

        PotenotStringResponse responseBody = webTestClient.post()
                .uri("potenot/str")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(potenotStringRequestList), PotenotStringRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PotenotStringResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(expectHeader, responseBody.getHeader());
        assertEquals(expectX, responseBody.getPointX());
        assertEquals(expectY, responseBody.getPointY());
    }

}
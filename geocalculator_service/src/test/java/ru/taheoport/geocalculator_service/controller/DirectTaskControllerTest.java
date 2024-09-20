package ru.taheoport.geocalculator_service.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import ru.taheoport.geocalculator_service.service.DirectTaskService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class DirectTaskControllerTest {

    @Autowired
    private DirectTaskService directTaskService;

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "2000000, 2000000, 0, 1000000, 1000000, 100000, 108000, 100000, 120, 1025882, 1096593, 100058",
            "2000000, 2000000, 108000, 1000000, 1000000, 100000, 1042872, 200156, -825, 1113906, 835418, 99199"})

    void solveDirectTaskTest(
            long landmarkX,
            long landmarkY,
            long landmarkDirection,
            long baseX,
            long baseY,
            long baseZ,
            long targetDirection,
            long targetInclinedDistance,
            long targetTiltAngle,
            long expectTargetX,
            long expectTargetY,
            long expectTargetZ
    ) {
        DirectTaskRequest  directTaskRequest  = new DirectTaskRequest();
        directTaskRequest.setLandmarkX(landmarkX);
        directTaskRequest.setLandmarkY(landmarkY);
        directTaskRequest.setLandmarkDirection(landmarkDirection);
        directTaskRequest.setBaseX(baseX);
        directTaskRequest.setBaseY(baseY);
        directTaskRequest.setBaseZ(baseZ);
        directTaskRequest.setTargetDirection(targetDirection);
        directTaskRequest.setTargetInclinedDistance(targetInclinedDistance);
        directTaskRequest.setTargetTiltAngle(targetTiltAngle);

        DirectTaskResponse responseBody = webTestClient.post()
                .uri("direct")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(directTaskRequest), DirectTaskRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(DirectTaskResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(expectTargetX, responseBody.getTargetX());
        assertEquals(expectTargetY, responseBody.getTargetY());
        assertEquals(expectTargetZ, responseBody.getTargetZ());
    }
}
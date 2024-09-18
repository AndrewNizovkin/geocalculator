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
import reactor.core.publisher.Mono;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;
import ru.taheoport.geocalculator_service.service.InverseTaskService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class InverseTackControllerTest {

    @Autowired
    private InverseTaskService inverseTaskService;

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "1000000000, 1000000000, 100000, 2000000000, 2000000000, 200000, 162000, 1414213562, 1414213566, 14, 100000"
    })
    void solveInverseTaskTest(
            long baseX,
            long baseY,
            long baseZ,
            long targetX,
            long targetY,
            long targetZ,
            long expectDirection,
            long expectHorDistance,
            long expectInclinedDistance,
            long expectTiltAngle,
            long expectElevation
    ) {
        InverseTaskRequest inverseTaskRequest = new InverseTaskRequest();
        inverseTaskRequest.setBaseX(baseX);
        inverseTaskRequest.setBaseY(baseY);
        inverseTaskRequest.setBaseZ(baseZ);
        inverseTaskRequest.setTargetX(targetX);
        inverseTaskRequest.setTargetY(targetY);
        inverseTaskRequest.setTargetZ(targetZ);

        InverseTaskResponse responseBody = webTestClient.post()
                .uri("inverse")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(inverseTaskRequest), InverseTaskRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InverseTaskResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(expectDirection, responseBody.getDirection(), 1);
        assertEquals(expectHorDistance, responseBody.getHorDistance(), 1);
        assertEquals(expectInclinedDistance, responseBody.getInclinedDistance(), 1);
        assertEquals(expectTiltAngle, responseBody.getTiltAngle(), 1);
        assertEquals(expectElevation, responseBody.getElevation(), 1);

    }
}
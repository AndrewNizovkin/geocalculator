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
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;
import ru.taheoport.geocalculator_service.dto.InverseStringResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.service.InverseTaskService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class InverseControllerTest {

    @Autowired
    private InverseTaskService inverseTaskService;

    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "1000000000, 1000000000, 100000, 2000000000, 2000000000, 200000, 162000, 1414213562, 1414213566, 15, 100000"
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

        InverseTaskFullResponse responseBody = webTestClient.post()
                .uri("inverse")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(inverseTaskRequest), InverseTaskRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(InverseTaskFullResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(baseX, responseBody.getBaseX());
        assertEquals(baseY, responseBody.getBaseY());
        assertEquals(baseZ, responseBody.getBaseZ());
        assertEquals(targetX, responseBody.getTargetX());
        assertEquals(targetY, responseBody.getTargetY());
        assertEquals(targetZ, responseBody.getTargetZ());
        assertEquals(expectDirection, responseBody.getDirection(), 1);
        assertEquals(expectHorDistance, responseBody.getHorDistance(), 1);
        assertEquals(expectInclinedDistance, responseBody.getInclinedDistance(), 1);
        assertEquals(expectTiltAngle, responseBody.getTiltAngle(), 1);
        assertEquals(expectElevation, responseBody.getElevation(), 1);
    }

    @ParameterizedTest
    @CsvSource({
            "0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.0000, 0.000, 0.000, 0.0000, 0.000, 'OK'",
            "1000000.000, 1000000.000, 100.000, 2000000.000, 2000000.000, 200.000, 45.0000, 1414213.562, 1414213.566, 0.0015, 100.000, 'OK'",
            "1badData00.000, 1000000.000, 100.000, 2000000.000, 2000000.000, 200.000, 0.0000, 0.000, 0.000, 0.0000, 0.000, 'Invalid base X'"
    })
    void getInverseStringResponseTest(
            String baseX,
            String baseY,
            String baseZ,
            String targetX,
            String targetY,
            String targetZ,
            String expectDirection,
            String expectHorDistance,
            String expectInclinedDistance,
            String expectTiltAngle,
            String expectElevation,
            String expectHeader

    ) {
        InverseStringRequest inverseTaskRequest = new InverseStringRequest();
        inverseTaskRequest.setBaseX(baseX);
        inverseTaskRequest.setBaseY(baseY);
        inverseTaskRequest.setBaseZ(baseZ);
        inverseTaskRequest.setTargetX(targetX);
        inverseTaskRequest.setTargetY(targetY);
        inverseTaskRequest.setTargetZ(targetZ);

        InverseStringResponse responseBody = webTestClient.post()
                .uri("inverse/str")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(inverseTaskRequest), InverseTaskRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(InverseStringResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(expectHeader, responseBody.getHeader());
        assertEquals(expectDirection, responseBody.getDirection());
        assertEquals(expectHorDistance, responseBody.getHorDistance());
        assertEquals(expectInclinedDistance, responseBody.getInclinedDistance());
        assertEquals(expectTiltAngle, responseBody.getTiltAngle());
        assertEquals(expectElevation, responseBody.getElevation());
    }

}
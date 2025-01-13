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
import ru.taheoport.geocalculator_service.dto.DirectTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import ru.taheoport.geocalculator_service.service.DirectTaskService;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "2000000, 2000000, 0, 1000000, 1000000, 100000, 0, 108000, 100000, 120, 0, 1025882, 1096593, 100058",
            "478685352, 2296938168, 0, 478676113, 2296967264, 11220, 1538, 668748, 39878, 406, 1600, 478660289, 2297003868, 11236",
            "2000000, 2000000, 108000, 1000000, 1000000, 100000, 0, 1042872, 200156, -825, 0, 1113906, 835418, 99199"})
    void solveDirectTaskTest(
            long landmarkX,
            long landmarkY,
            long landmarkDirection,
            long baseX,
            long baseY,
            long baseZ,
            long baseHeight,
            long targetDirection,
            long targetInclinedDistance,
            long targetTiltAngle,
            long targetHeight,
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
        directTaskRequest.setBaseHeight(baseHeight);
        directTaskRequest.setTargetDirection(targetDirection);
        directTaskRequest.setTargetInclinedDistance(targetInclinedDistance);
        directTaskRequest.setTargetTiltAngle(targetTiltAngle);
        directTaskRequest.setTargetHeight(targetHeight);

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

    @Test
    void getDirectTaskFullResponse() {

        List<DirectTaskFullResponse> expectList = getDirectTaskFullResponses();

        List<DirectTaskRequest> directTaskRequests = new LinkedList<>(expectList);

        List<DirectTaskFullResponse> responseBody = webTestClient.post()
                .uri("direct/full")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(directTaskRequests), DirectTaskRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(DirectTaskFullResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertEquals(expectList.size(), responseBody.size());
        for (DirectTaskFullResponse response: responseBody) {
            boolean found = expectList.stream()
                    .filter(expectResponse -> Objects.equals(response.getLandmarkX(), expectResponse.getLandmarkX()))
                    .filter(expectResponse -> Objects.equals(response.getLandmarkY(), expectResponse.getLandmarkY()))
                    .filter(expectResponse -> Objects.equals(response.getLandmarkDirection(), expectResponse.getLandmarkDirection()))
                    .filter(expectResponse -> Objects.equals(response.getBaseX(), expectResponse.getBaseX()))
                    .filter(expectResponse -> Objects.equals(response.getBaseY(), expectResponse.getBaseY()))
                    .filter(expectResponse -> Objects.equals(response.getBaseZ(), expectResponse.getBaseZ()))
                    .filter(expectResponse -> Objects.equals(response.getBaseHeight(), expectResponse.getBaseHeight()))
                    .filter(expectResponse -> Objects.equals(response.getTargetDirection(), expectResponse.getTargetDirection()))
                    .filter(expectResponse -> Objects.equals(response.getTargetInclinedDistance(), expectResponse.getTargetInclinedDistance()))
                    .filter(expectResponse -> Objects.equals(response.getTargetTiltAngle(), expectResponse.getTargetTiltAngle()))
                    .filter(expectResponse -> Objects.equals(response.getTargetHeight(), expectResponse.getTargetHeight()))
                    .filter(expectResponse -> Objects.equals(response.getTargetX(), expectResponse.getTargetX()))
                    .filter(expectResponse -> Objects.equals(response.getTargetY(), expectResponse.getTargetY()))
                    .anyMatch(expectResponse -> Objects.equals(response.getTargetZ(), expectResponse.getTargetZ()));
            assertTrue(found);
        }


    }

    private List<DirectTaskFullResponse> getDirectTaskFullResponses() {
        List<DirectTaskFullResponse> expectList = new LinkedList<>();
        DirectTaskFullResponse directTaskFullResponse = new DirectTaskFullResponse();
        directTaskFullResponse.setLandmarkX(0);
        directTaskFullResponse.setLandmarkY(0);
        directTaskFullResponse.setLandmarkDirection(0);
        directTaskFullResponse.setBaseX(0);
        directTaskFullResponse.setBaseY(0);
        directTaskFullResponse.setBaseZ(0);
        directTaskFullResponse.setBaseHeight(0);
        directTaskFullResponse.setTargetDirection(0);
        directTaskFullResponse.setTargetInclinedDistance(0);
        directTaskFullResponse.setTargetTiltAngle(0);
        directTaskFullResponse.setTargetHeight(0);
        directTaskFullResponse.setTargetX(0);
        directTaskFullResponse.setTargetY(0);
        directTaskFullResponse.setTargetZ(0);
        expectList.add(directTaskFullResponse);

        directTaskFullResponse.setLandmarkX(2000000);
        directTaskFullResponse.setLandmarkY(2000000);
        directTaskFullResponse.setLandmarkDirection(0);
        directTaskFullResponse.setBaseX(1000000);
        directTaskFullResponse.setBaseY(1000000);
        directTaskFullResponse.setBaseZ(100000);
        directTaskFullResponse.setBaseHeight(0);
        directTaskFullResponse.setTargetDirection(108000);
        directTaskFullResponse.setTargetInclinedDistance(100000);
        directTaskFullResponse.setTargetTiltAngle(120);
        directTaskFullResponse.setTargetHeight(0);
        directTaskFullResponse.setTargetX(1025882);
        directTaskFullResponse.setTargetY(1096593);
        directTaskFullResponse.setTargetZ(100058);
        expectList.add(directTaskFullResponse);

        directTaskFullResponse.setLandmarkX(478685352L);
        directTaskFullResponse.setLandmarkY(2296938168L);
        directTaskFullResponse.setLandmarkDirection(0);
        directTaskFullResponse.setBaseX(478676113L);
        directTaskFullResponse.setBaseY(2296967264L);
        directTaskFullResponse.setBaseZ(11220);
        directTaskFullResponse.setBaseHeight(1538);
        directTaskFullResponse.setTargetDirection(668748);
        directTaskFullResponse.setTargetInclinedDistance(39878);
        directTaskFullResponse.setTargetTiltAngle(406);
        directTaskFullResponse.setTargetHeight(1600);
        directTaskFullResponse.setTargetX(478660289);
        directTaskFullResponse.setTargetY(2297003868L);
        directTaskFullResponse.setTargetZ(11236);
        expectList.add(directTaskFullResponse);
        return expectList;
    }
}
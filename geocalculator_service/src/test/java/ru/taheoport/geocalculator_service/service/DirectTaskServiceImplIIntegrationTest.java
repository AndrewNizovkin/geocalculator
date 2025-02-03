package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.DirectStringRequest;
import ru.taheoport.geocalculator_service.dto.DirectStringResponse;
import ru.taheoport.geocalculator_service.dto.DirectTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.mapper.DataMapperDefault;
import ru.taheoport.geocalculator_service.mapper.DirectCalculatorDefault;
import ru.taheoport.geocalculator_service.mapper.DirectTaskMapperDefault;
import ru.taheoport.geocalculator_service.mapper.InverseCalculatorImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        DirectTaskServiceImpl.class,
        DirectTaskMapperDefault.class,
        DirectCalculatorDefault.class,
        DataMapperDefault.class,
        InverseCalculatorImpl.class
})
class DirectTaskServiceImplIIntegrationTest {

    @Autowired
    private DirectTaskService directTaskService;

    @Test
    void getDirectTaskFullResponse() {
        List<DirectTaskFullResponse> expectResponses = getFullResponses();
        List<DirectTaskRequest> directTaskRequests = new LinkedList<>(expectResponses);

        List<DirectTaskFullResponse> actualResponses = directTaskService.getDirectTaskFullResponse(directTaskRequests);

        assertNotNull(actualResponses);
        assertEquals(expectResponses.size(), actualResponses.size());
        for (DirectTaskFullResponse response: actualResponses) {
            boolean found = expectResponses.stream()
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

    @Test
    void getDirectStringResponse() {
        DirectStringResponse expectResponse = getStringResponse();
        DirectStringRequest directStringRequest = getStringRequest();

        DirectStringResponse actualResponse = directTaskService.getDirectStringResponse(directStringRequest);

        assertNotNull(actualResponse);
        assertEquals(expectResponse.getLandmarkX(), actualResponse.getLandmarkX());
        assertEquals(expectResponse.getLandmarkY(), actualResponse.getLandmarkY());
        assertEquals(expectResponse.getLandmarkDirection(), actualResponse.getLandmarkDirection());
        assertEquals(expectResponse.getBaseX(), actualResponse.getBaseX());
        assertEquals(expectResponse.getBaseY(), actualResponse.getBaseY());
        assertEquals(expectResponse.getBaseZ(), actualResponse.getBaseZ());
        assertEquals(expectResponse.getBaseHeight(), actualResponse.getBaseHeight());
        assertEquals(expectResponse.getTargetDirection(), actualResponse.getTargetDirection());
        assertEquals(expectResponse.getTargetInclinedDistance(), actualResponse.getTargetInclinedDistance());
        assertEquals(expectResponse.getTargetTiltAngle(), actualResponse.getTargetTiltAngle());
        assertEquals(expectResponse.getTargetHeight(), actualResponse.getTargetHeight());
        assertEquals(expectResponse.getTargetX(), actualResponse.getTargetX());
        assertEquals(expectResponse.getTargetY(), actualResponse.getTargetY());
        assertEquals(expectResponse.getTargetZ(), actualResponse.getTargetZ());

    }

    /**
     * Gets instance of DirectStringResponse
     * @return directStringResponse
     */
    private DirectStringResponse getStringResponse() {
        DirectStringResponse expectResponse = new DirectStringResponse();
        expectResponse.setLandmarkX("478685.352");
        expectResponse.setLandmarkY("2296938.168");
        expectResponse.setLandmarkDirection("0.0000");
        expectResponse.setBaseX("478676.113");
        expectResponse.setBaseY("2296967.264");
        expectResponse.setBaseZ("11.220");
        expectResponse.setBaseHeight("1.538");
        expectResponse.setTargetDirection("185.4548");
        expectResponse.setTargetInclinedDistance("39.878");
        expectResponse.setTargetTiltAngle("0.0646");
        expectResponse.setTargetHeight("1.600");
        expectResponse.setTargetX("478660.289");
        expectResponse.setTargetY("2297003.868");
        expectResponse.setTargetZ("11.236");
        return expectResponse;
    }

    /**
     * Gets instance of DirectStringRequest
     * @return DirectStringRequest
     */
    private DirectStringRequest getStringRequest() {
        DirectStringRequest directStringRequest = new DirectStringRequest();
        directStringRequest.setLandmarkX("478685.352");
        directStringRequest.setLandmarkY("2296938.168");
        directStringRequest.setLandmarkDirection("0.0000");
        directStringRequest.setBaseX("478676.113");
        directStringRequest.setBaseY("2296967.264");
        directStringRequest.setBaseZ("11.220");
        directStringRequest.setBaseHeight("1.538");
        directStringRequest.setTargetDirection("185.4548");
        directStringRequest.setTargetInclinedDistance("39.878");
        directStringRequest.setTargetTiltAngle("0.0646");
        directStringRequest.setTargetHeight("1.600");
        return directStringRequest;
    }

    /**
     * Gets test list of data, measurements and processing results
     * @return List of DirectTaskFullResponse
     */
    private List<DirectTaskFullResponse> getFullResponses() {
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
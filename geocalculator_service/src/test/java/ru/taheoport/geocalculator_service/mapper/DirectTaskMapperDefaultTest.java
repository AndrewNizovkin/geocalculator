package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        DirectTaskMapperDefault.class,
        InverseCalculatorImpl.class,
        DataMapperDefault.class,
        DirectCalculatorDefault.class})
class DirectTaskMapperDefaultTest {

    @Autowired
    private DirectTaskMapper directTaskMapper;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "2000000, 2000000, 0, 1000000, 1000000, 100000, 0, 108000, 100000, 120, 0, 1025882, 1096593, 100058",
            "478685352, 2296938168, 0, 478676113, 2296967264, 11220, 1538, 668748, 39878, 406, 1600, 478660289, 2297003868, 11236",
            "2000000, 2000000, 108000, 1000000, 1000000, 100000, 0, 1042872, 200156, -825, 0, 1113906, 835418, 99199"})
    void toDirectTaskResponseTest(
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
        DirectTaskRequest directTaskRequest = new DirectTaskRequest();
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

        DirectTaskResponse actualDirectTaskResponse = directTaskMapper.toDirectTaskResponse(directTaskRequest);

        assertNotNull(actualDirectTaskResponse);
        assertEquals(expectTargetX, actualDirectTaskResponse.getTargetX());
        assertEquals(expectTargetY, actualDirectTaskResponse.getTargetY());
        assertEquals(expectTargetZ, actualDirectTaskResponse.getTargetZ());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "2000000, 2000000, 0, 1000000, 1000000, 100000, 0, 108000, 100000, 120, 0, 1025882, 1096593, 100058",
            "478685352, 2296938168, 0, 478676113, 2296967264, 11220, 1538, 668748, 39878, 406, 1600, 478660289, 2297003868, 11236",
            "2000000, 2000000, 108000, 1000000, 1000000, 100000, 0, 1042872, 200156, -825, 0, 1113906, 835418, 99199"})
    void toDirectTaskFullResponseTest(
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
        DirectTaskRequest directTaskRequest = new DirectTaskRequest();
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

        DirectTaskFullResponse actualDirectTaskFullResponse = directTaskMapper.toDirectTaskFullResponse(directTaskRequest);

        assertNotNull(actualDirectTaskFullResponse);
        assertEquals(expectTargetX, actualDirectTaskFullResponse.getTargetX());
        assertEquals(expectTargetY, actualDirectTaskFullResponse.getTargetY());
        assertEquals(expectTargetZ, actualDirectTaskFullResponse.getTargetZ());
        assertEquals(landmarkX, actualDirectTaskFullResponse.getLandmarkX());
        assertEquals(landmarkY, actualDirectTaskFullResponse.getLandmarkY());
        assertEquals(landmarkDirection, actualDirectTaskFullResponse.getLandmarkDirection());
        assertEquals(baseX, actualDirectTaskFullResponse.getBaseX());
        assertEquals(baseY, actualDirectTaskFullResponse.getBaseY());
        assertEquals(baseZ, actualDirectTaskFullResponse.getBaseZ());
        assertEquals(baseHeight, actualDirectTaskFullResponse.getBaseHeight());
        assertEquals(targetDirection, actualDirectTaskFullResponse.getTargetDirection());
        assertEquals(targetInclinedDistance, actualDirectTaskFullResponse.getTargetInclinedDistance());
        assertEquals(targetTiltAngle, actualDirectTaskFullResponse.getTargetTiltAngle());
        assertEquals(targetHeight, actualDirectTaskFullResponse.getTargetHeight());
    }

    @Test
    void toDirectTaskRequestTest() {
        DirectTaskRequest expectRequest = getDirectTaskRequest();
        DirectStringRequest directStringRequest = getDirectStringRequest();

        DirectTaskRequest actualRequest = directTaskMapper.toDirectTaskRequest(directStringRequest);

        assertNotNull(actualRequest);

        assertEquals(expectRequest.getLandmarkX(), actualRequest.getLandmarkX());
        assertEquals(expectRequest.getLandmarkY(), actualRequest.getLandmarkY());
        assertEquals(expectRequest.getLandmarkDirection(), actualRequest.getLandmarkDirection());
        assertEquals(expectRequest.getBaseX(), actualRequest.getBaseX());
        assertEquals(expectRequest.getBaseY(), actualRequest.getBaseY());
        assertEquals(expectRequest.getBaseZ(), actualRequest.getBaseZ());
        assertEquals(expectRequest.getBaseHeight(), actualRequest.getBaseHeight());
        assertEquals(expectRequest.getTargetDirection(), actualRequest.getTargetDirection());
        assertEquals(expectRequest.getTargetInclinedDistance(), actualRequest.getTargetInclinedDistance());
        assertEquals(expectRequest.getTargetTiltAngle(), actualRequest.getTargetTiltAngle());
        assertEquals(expectRequest.getTargetHeight(), actualRequest.getTargetHeight());
    }

    @Test
    void toDirectStringResponseTest(){
        DirectStringResponse expectResponse = getDirectStringResponse();
        DirectTaskFullResponse directTaskFullResponse = getDirectTaskFullResponse();

        DirectStringResponse actualResponse = directTaskMapper.toDirectStringResponse(directTaskFullResponse);

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
     * Gets test instance of DirectTaskFullResponse
     * @return DirectTaskFullResponse
     */
    private DirectTaskFullResponse getDirectTaskFullResponse() {
        DirectTaskFullResponse directTaskFullResponse = new DirectTaskFullResponse();
        directTaskFullResponse.setLandmarkX(478685352);
        directTaskFullResponse.setLandmarkY(2296938168L);
        directTaskFullResponse.setLandmarkDirection(0);
        directTaskFullResponse.setBaseX(478676113);
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
        return directTaskFullResponse;
    }

    /**
     * Gets instance of DirectStringResponse
     * @return directStringResponse
     */
    private DirectStringResponse getDirectStringResponse() {
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
    private DirectStringRequest getDirectStringRequest() {
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
     * Gets instance of DirectTaskRequest
     * @return DirectTaskRequest
     */
    private DirectTaskRequest getDirectTaskRequest() {
        DirectTaskRequest expectRequest = new DirectTaskRequest();
        expectRequest.setLandmarkX(478685352);
        expectRequest.setLandmarkY(2296938168L);
        expectRequest.setLandmarkDirection(0);
        expectRequest.setBaseX(478676113);
        expectRequest.setBaseY(2296967264L);
        expectRequest.setBaseZ(11220);
        expectRequest.setBaseHeight(1538);
        expectRequest.setTargetDirection(668748);
        expectRequest.setTargetInclinedDistance(39878);
        expectRequest.setTargetTiltAngle(406);
        expectRequest.setTargetHeight(1600);
        return expectRequest;
    }


}
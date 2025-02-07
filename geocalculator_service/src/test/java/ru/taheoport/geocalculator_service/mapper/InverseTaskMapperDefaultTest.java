package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;
import ru.taheoport.geocalculator_service.dto.InverseStringResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        InverseTaskMapperDefault.class,
        InverseCalculatorImpl.class,
        DataMapperDefault.class
})
class InverseTaskMapperDefaultTest {

    @Autowired
    private InverseTaskMapper inverseTaskMapper;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "1000000000, 1000000000, 100000, 2000000000, 2000000000, 200000, 162000, 1414213562, 1414213566, 15, 100000"
    })
    void toInverseTaskFullResponseTest(
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

        InverseTaskFullResponse actualResponse = inverseTaskMapper.toInverseTaskFullResponse(inverseTaskRequest);

        assertNotNull(actualResponse);
        assertEquals(baseX, actualResponse.getBaseX());
        assertEquals(baseY, actualResponse.getBaseY());
        assertEquals(baseZ, actualResponse.getBaseZ());
        assertEquals(targetX, actualResponse.getTargetX());
        assertEquals(targetY, actualResponse.getTargetY());
        assertEquals(targetZ, actualResponse.getTargetZ());
        assertEquals(expectDirection, actualResponse.getDirection(), 1);
        assertEquals(expectHorDistance, actualResponse.getHorDistance(), 1);
        assertEquals(expectInclinedDistance, actualResponse.getInclinedDistance(), 1);
        assertEquals(expectTiltAngle, actualResponse.getTiltAngle(), 1);
        assertEquals(expectElevation, actualResponse.getElevation(), 1);
    }

    @Test
    void toInverseTaskRequestTest() {
        InverseTaskRequest expectInverseTaskRequest = new InverseTaskRequest();
        expectInverseTaskRequest.setBaseX(0);
        expectInverseTaskRequest.setBaseY(2345678234L);
        expectInverseTaskRequest.setBaseZ(-12345);
        expectInverseTaskRequest.setTargetX(467893123);
        expectInverseTaskRequest.setTargetY(2345678000L);
        expectInverseTaskRequest.setTargetZ(8789987123L);
        InverseStringRequest inverseStringRequest = getInverseStringRequest();

        InverseTaskRequest actualInverseTaskRequest = inverseTaskMapper.toInverseTaskRequest(inverseStringRequest);

        assertNotNull(actualInverseTaskRequest);
        assertEquals(expectInverseTaskRequest.getBaseX(), actualInverseTaskRequest.getBaseX());
        assertEquals(expectInverseTaskRequest.getBaseY(), actualInverseTaskRequest.getBaseY());
        assertEquals(expectInverseTaskRequest.getBaseZ(), actualInverseTaskRequest.getBaseZ());
        assertEquals(expectInverseTaskRequest.getTargetX(), actualInverseTaskRequest.getTargetX());
        assertEquals(expectInverseTaskRequest.getTargetY(), actualInverseTaskRequest.getTargetY());
        assertEquals(expectInverseTaskRequest.getTargetZ(), actualInverseTaskRequest.getTargetZ());
    }

    @Test
    void toInverseStringResponseTest() {
        InverseTaskFullResponse inverseTaskFullResponse = getInverseTaskFullResponse();
        InverseStringResponse expectResponse = getInverseStringResponse();

        InverseStringResponse actualResponse = inverseTaskMapper.toInverseStringResponse(inverseTaskFullResponse);

        assertNotNull(actualResponse);
        assertEquals(expectResponse.getDirection(), actualResponse.getDirection());
        assertEquals(expectResponse.getHorDistance(), actualResponse.getHorDistance());
        assertEquals(expectResponse.getInclinedDistance(), actualResponse.getInclinedDistance());
        assertEquals(expectResponse.getTiltAngle(), actualResponse.getTiltAngle());
        assertEquals(expectResponse.getElevation(), actualResponse.getElevation());
    }


    /**
     * Gives test instance of InverseStringResponse
     * @return inverseStringResponse
     */
    private InverseStringResponse getInverseStringResponse() {
        InverseStringResponse expectResponse = new InverseStringResponse();
        expectResponse.setDirection("45.0000");
        expectResponse.setHorDistance("1414213.562");
        expectResponse.setInclinedDistance("1414213.566");
        expectResponse.setTiltAngle("0.0015");
        expectResponse.setElevation("100.000");
        return expectResponse;
    }

    /**
     * Gives test instance of InverseTaskFullResponse
     * @return inverseTaskFullResponse
     */
    private InverseTaskFullResponse getInverseTaskFullResponse() {
        InverseTaskFullResponse inverseTaskFullResponse = new InverseTaskFullResponse();
        inverseTaskFullResponse.setBaseX(1000000000);
        inverseTaskFullResponse.setBaseY(1000000000);
        inverseTaskFullResponse.setBaseZ(100000);
        inverseTaskFullResponse.setTargetX(2000000000);
        inverseTaskFullResponse.setTargetY(2000000000);
        inverseTaskFullResponse.setTargetZ(200000);
        inverseTaskFullResponse.setDirection(162000);
        inverseTaskFullResponse.setHorDistance(1414213562);
        inverseTaskFullResponse.setInclinedDistance(1414213566);
        inverseTaskFullResponse.setTiltAngle(15);
        inverseTaskFullResponse.setElevation(100000);
        return inverseTaskFullResponse;
    }

    /**
     * Gives test instance of InverseStringRequest
     * @return InverseStringRequest
     */
    private InverseStringRequest getInverseStringRequest() {
        InverseStringRequest inverseStringRequest = new InverseStringRequest();
        inverseStringRequest.setBaseX("0.000");
        inverseStringRequest.setBaseY("2345678.234");
        inverseStringRequest.setBaseZ("-12.345");
        inverseStringRequest.setTargetX("467893.123");
        inverseStringRequest.setTargetY("2345678.000");
        inverseStringRequest.setTargetZ("8789987.123");
        return inverseStringRequest;
    }
}
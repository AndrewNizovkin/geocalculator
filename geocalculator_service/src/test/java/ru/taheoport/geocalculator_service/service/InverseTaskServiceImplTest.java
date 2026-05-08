package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;
import ru.taheoport.geocalculator_service.dto.InverseStringResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.mapper.DataMapperDefault;
import ru.taheoport.geocalculator_service.mapper.InverseCalculatorImpl;
import ru.taheoport.geocalculator_service.mapper.InverseTaskMapperDefault;
import ru.taheoport.geocalculator_service.validator.DataValidatorDefault;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        InverseTaskServiceImpl.class,
        InverseTaskMapperDefault.class,
        InverseCalculatorImpl.class,
        DataMapperDefault.class,
        DataValidatorDefault.class
})
class InverseTaskServiceImplTest {

    @Autowired
    private InverseTaskService inverseTaskService;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "1000000000, 1000000000, 100000, 2000000000, 2000000000, 200000, 162000, 1414213562, 1414213566, 15, 100000"
    })
    void getInverseTaskFullResponse(
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

        InverseTaskFullResponse actualResponse = inverseTaskService.getInverseTaskFullResponse(inverseTaskRequest);

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

    @ParameterizedTest
    @CsvSource({
            "0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.0000, 0.000, 0.000, 0.0000, 0.000",
            "1000000.000, 1000000.000, 100.000, 2000000.000, 2000000.000, 200.000, 45.0000, 1414213.562, 1414213.566, 0.0015, 100.000"
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
            String expectElevation

    ) {
        InverseStringRequest inverseTaskRequest = new InverseStringRequest();
        inverseTaskRequest.setBaseX(baseX);
        inverseTaskRequest.setBaseY(baseY);
        inverseTaskRequest.setBaseZ(baseZ);
        inverseTaskRequest.setTargetX(targetX);
        inverseTaskRequest.setTargetY(targetY);
        inverseTaskRequest.setTargetZ(targetZ);
        String expectHeader = "OK";

        InverseStringResponse actualResponse = inverseTaskService.getInverseStringResponse(inverseTaskRequest);

        assertNotNull(actualResponse);
        assertEquals(expectHeader, actualResponse.getHeader());
        assertEquals(expectDirection, actualResponse.getDirection());
        assertEquals(expectHorDistance, actualResponse.getHorDistance());
        assertEquals(expectInclinedDistance, actualResponse.getInclinedDistance());
        assertEquals(expectTiltAngle, actualResponse.getTiltAngle());
        assertEquals(expectElevation, actualResponse.getElevation());
    }

    @ParameterizedTest
    @CsvSource({
            "1000000#000, 1000000.000, 100.000, 2000000.000, 2000000.000, 200.000, 'Invalid base X'",
            "1000000.000, l000000.000, 100.000, 2000000.000, 2000000.000, 200.000, 'Invalid base Y'",
            "1000000.000, 1000000.000, 100.00O, 2000000.000, 2000000.000, 200.000, 'Invalid base Z'",
            "1000000.000, 1000000.000, 100.000, 2f00000.000, 2000000.000, 200.000, 'Invalid target X'",
            "1000000.000, 1000000.000, 100.000, 2000000.000, 2000()000.000, 200.000, 'Invalid target Y'",
            "1000000.000, 1000000.000, 100.000, 2000000.000, 2000000.000, 200_000, 'Invalid target Z'"
    })
    void getInverseStringResponseBadDataTest(
            String baseX,
            String baseY,
            String baseZ,
            String targetX,
            String targetY,
            String targetZ,
            String expectHeader
    ) {
        InverseStringRequest inverseTaskRequest = new InverseStringRequest();
        inverseTaskRequest.setBaseX(baseX);
        inverseTaskRequest.setBaseY(baseY);
        inverseTaskRequest.setBaseZ(baseZ);
        inverseTaskRequest.setTargetX(targetX);
        inverseTaskRequest.setTargetY(targetY);
        inverseTaskRequest.setTargetZ(targetZ);

        InverseStringResponse actualResponse = inverseTaskService.getInverseStringResponse(inverseTaskRequest);

        assertNotNull(actualResponse);
        assertEquals(expectHeader, actualResponse.getHeader());
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
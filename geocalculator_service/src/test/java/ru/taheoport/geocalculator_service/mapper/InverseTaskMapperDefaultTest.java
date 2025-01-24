package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InverseTaskMapperDefault.class, InverseCalculatorImpl.class})
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
}
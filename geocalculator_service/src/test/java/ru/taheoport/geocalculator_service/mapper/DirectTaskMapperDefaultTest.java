package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.DirectTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        DirectTaskMapperDefault.class,
InverseCalculatorImpl.class,
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


}
package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;

@SpringBootTest(classes = {
        DirectTaskMapperDefault.class,
InverseCalculatorImpl.class,
DirectCalculatorDefault.class})
class DirectTaskMapperDefaultTest {

    @Autowired
    private DirectTaskMapper directTaskMapper;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0",
            "2000000, 2000000, 0, 1000000, 1000000, 100000, 108000, 100000, 120, 1025882, 1096593, 100058",
            "2000000, 2000000, 108000, 1000000, 1000000, 100000, 1042872, 200156, -825, 1113906, 835418, 99199"})
    void toDirectTaskResponseTest(
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
        DirectTaskRequest directTaskRequest = new DirectTaskRequest();
        directTaskRequest.setLandmarkX(landmarkX);
        directTaskRequest.setLandmarkY(landmarkY);
        directTaskRequest.setLandmarkDirection(landmarkDirection);
        directTaskRequest.setBaseX(baseX);
        directTaskRequest.setBaseY(baseY);
        directTaskRequest.setBaseZ(baseZ);
        directTaskRequest.setTargetDirection(targetDirection);
        directTaskRequest.setTargetInclinedDistance(targetInclinedDistance);
        directTaskRequest.setTargetTiltAngle(targetTiltAngle);

        DirectTaskResponse actualDirectTaskResponse = directTaskMapper.toDirectTaskResponse(directTaskRequest);

        Assertions.assertEquals(expectTargetX, actualDirectTaskResponse.getTargetX());
        Assertions.assertEquals(expectTargetY, actualDirectTaskResponse.getTargetY());
        Assertions.assertEquals(expectTargetZ, actualDirectTaskResponse.getTargetZ());
    }
}
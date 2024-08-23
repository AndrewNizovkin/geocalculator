package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.DirectTaskDto;

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
    void toDirectTaskDtoTest(
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
        DirectTaskDto directTaskDto = new DirectTaskDto();
        directTaskDto.setLandmarkX(landmarkX);
        directTaskDto.setLandmarkY(landmarkY);
        directTaskDto.setLandmarkDirection(landmarkDirection);
        directTaskDto.setBaseX(baseX);
        directTaskDto.setBaseY(baseY);
        directTaskDto.setBaseZ(baseZ);
        directTaskDto.setTargetDirection(targetDirection);
        directTaskDto.setTargetInclinedDistance(targetInclinedDistance);
        directTaskDto.setTargetTiltAngle(targetTiltAngle);

        DirectTaskDto actualDirectTaskDto = directTaskMapper.toDirectTaskDto(directTaskDto);

        Assertions.assertEquals(expectTargetX, actualDirectTaskDto.getTargetX());
        Assertions.assertEquals(expectTargetY, actualDirectTaskDto.getTargetY());
        Assertions.assertEquals(expectTargetZ, actualDirectTaskDto.getTargetZ());
    }
}
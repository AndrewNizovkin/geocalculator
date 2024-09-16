package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;

import java.util.List;

@SpringBootTest(classes = {PotenotTaskMapperImpl.class, PotenotCalculatorImpl.class})
class PotenotTaskMapperImplTest {

    @Autowired
    private PotenotTaskMapper potenotTaskMapper;

    @ParameterizedTest
    @CsvSource({
            "100000, 100000, 904445, 200000, 200000, 1265914, 100000, 300000, 404065, 137114, 209238",
            "100000, 100000, 1195127, 200000, 200000, 62348, 100000, 300000, 224986, 18515, 143314",
            "100000, 100000, 1142287, 200000, 200000, 1168488, 100000, 300000, 1173462, -709005, 846436",
            "-100000, -100000, 587159, -200000, -200000, 662082, -100000, -300000, 742845, 150295, -176044"
    })
    void solvePotenotTaskTest(
            long firstX,
            long firstY,
            long firstDirection,
            long secondX,
            long secondY,
            long secondDirection,
            long thirdX,
            long thirdY,
            long thirdDirection,
            long expectX,
            long expectY
    ) {
        List<PotenotTaskRequest> potenotTaskRequestList = List.of(
                new PotenotTaskRequest(firstX, firstY, firstDirection),
                new PotenotTaskRequest(secondX, secondY, secondDirection),
                new PotenotTaskRequest(thirdX, thirdY, thirdDirection)
        );

        PotenotTaskResponse actualResponse = potenotTaskMapper.solvePotenotTask(potenotTaskRequestList);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectX, actualResponse.getPointX(), 1);
        Assertions.assertEquals(expectY, actualResponse.getPointY(), 1);

    }
}
package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.InverseTask;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InverseTaskCalculatorDefault.class})
class InverseTaskCalculatorDefaultTest {

    @Autowired
    private InverseTaskCalculator inverseTaskCalculator;

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 1000000, 2000000000, 2000000000, 11000, 1414213562",
            "-1000000000, -1000000000, -1000000, -2000000000, -2000000000, -1100000, 1414213562"
    })
    void getHorDistanceTest(long firstX,
                            long firstY,
                            long firstZ,
                            long secondX,
                            long secondY,
                            long secondZ,
                            long expect) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setBaseX(firstX);
        inverseTask.setBaseY(firstY);
        inverseTask.setBaseZ(firstZ);
        inverseTask.setTargetX(secondX);
        inverseTask.setTargetY(secondY);
        inverseTask.setTargetZ(secondZ);

        long actualResult = inverseTaskCalculator.getHorDistance(inverseTask);

        assertEquals(expect, actualResult);


    }


    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 1000000, 2000000000, 2000000000, 1100000, 1414213566",
            "-1000000000, -1000000000, -1000000, -2000000000, -2000000000, -1100000, 1414213566"
    })
    void getInclinedDistanceTest(long firstX,
                                 long firstY,
                                 long firstZ,
                                 long secondX,
                                 long secondY,
                                 long secondZ,
                                 long expect) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setBaseX(firstX);
        inverseTask.setBaseY(firstY);
        inverseTask.setBaseZ(firstZ);
        inverseTask.setTargetX(secondX);
        inverseTask.setTargetY(secondY);
        inverseTask.setTargetZ(secondZ);

        long actualResult = inverseTaskCalculator.getInclinedDistance(inverseTask);

        assertEquals(expect, actualResult);


    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 0, 1000000000, 2000000000, 0, 324000",
            "1000000000, 2000000000, 0, 1000000000, 1000000000, 0, 972000",
            "1000000000, 1000000000, 0, 1000000000, 1000000000, 0, 0",
            "1000000000, 1000000000, 0, 2000000000, 1000000000, 0, 0",
            "2000000000, 1000000000, 0, 1000000000, 1000000000, 0, 648000",
            "1000000000, 1000000000, 0, 2000000000, 2000000000, 0, 162000",
            "2000000000, 2000000000, 0, 1000000000, 1000000000, 0, 810000",
            "2000000000, 2000000000, 0, 1000000000, 3000000000, 0, 486000",
            "2000000000, 2000000000, 0, 3000000000, 1000000000, 0, 1134000",
            "0, 0, 0, 0, 0, 0, 0"
    })
    void getDirectionTest(long firstX,
                          long firstY,
                          long firstZ,
                          long secondX,
                          long secondY,
                          long secondZ,
                          long expect) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setBaseX(firstX);
        inverseTask.setBaseY(firstY);
        inverseTask.setBaseZ(firstZ);
        inverseTask.setTargetX(secondX);
        inverseTask.setTargetY(secondY);
        inverseTask.setTargetZ(secondZ);

        long actualResult = inverseTaskCalculator.getDirection(inverseTask);

        assertEquals(expect, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 0, 1000000000, 2000000000, 0, 0",
            "1000000000, 2000000000, 10000000, 1000000000, 1000000000, 10005555, 5555",
            "1000000000, 1000000000, 10000155, 1000000000, 1000000000, 0, -10000155",
             "0, 0, 0, 0, 0, 0, 0"
    })
    void getElevationTest(long firstX,
                          long firstY,
                          long firstZ,
                          long secondX,
                          long secondY,
                          long secondZ,
                          long expect) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setBaseX(firstX);
        inverseTask.setBaseY(firstY);
        inverseTask.setBaseZ(firstZ);
        inverseTask.setTargetX(secondX);
        inverseTask.setTargetY(secondY);
        inverseTask.setTargetZ(secondZ);

        long actualResult = inverseTaskCalculator.getElevation(inverseTask);

        assertEquals(expect, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 0, 1000000000, 2000000000, 1000000000, 162000",
            "1000000000, 1000000000, 1000000000, 1000000000, 2000000000, 0, -162000",
            "1000000000, 1000045000, 10000155, 1000000000, 2000000000, 10000155, 0",
            "1000130000, 1000045000, 10000155, 1000000000, 2000000000, 10135456, 28",
            "1000130000, 1000045000, 10135456, 1000000000, 2000000000, 10000155, -28",
            "0, 0, 0, 0, 0, 0, 0"
    })
    void getTiltAngleTest(long firstX,
                          long firstY,
                          long firstZ,
                          long secondX,
                          long secondY,
                          long secondZ,
                          long expect) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setBaseX(firstX);
        inverseTask.setBaseY(firstY);
        inverseTask.setBaseZ(firstZ);
        inverseTask.setTargetX(secondX);
        inverseTask.setTargetY(secondY);
        inverseTask.setTargetZ(secondZ);

        long actualResult = inverseTaskCalculator.getTiltAngle(inverseTask);

        assertEquals(expect, actualResult);
    }

}
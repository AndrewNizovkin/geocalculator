package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.InverseTask;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InverseCalculatorDefault.class})
class InverseCalculatorDefaultTest {

    @Autowired
    private InverseCalculator inverseCalculator;

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 2000000000, 2000000000, 1414213562",
            "-1000000000, -1000000000, -2000000000, -2000000000, 1414213562"
    })
    void getHorDistanceTest(long baseX,
                            long baseY,
                            long targetX,
                            long targetY,
                            long expect) {

        long actualResult = inverseCalculator.getHorDistance(baseX,
                baseY,
                targetX,
                targetY);

        assertEquals(expect, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 1000000, 2000000000, 2000000000, 1100000, 1414213566",
            "-1000000000, -1000000000, -1000000, -2000000000, -2000000000, -1100000, 1414213566"
    })
    void getInclinedDistanceTest(long baseX,
                                 long baseY,
                                 long baseZ,
                                 long targetX,
                                 long targetY,
                                 long targetZ,
                                 long expect) {

        long actualResult = inverseCalculator.getInclinedDistance(baseX,
                baseY,
                baseZ,
                targetX,
                targetY,
                targetZ);

        assertEquals(expect, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 1000000000, 2000000000, 324000",
            "1000000000, 2000000000, 1000000000, 1000000000, 972000",
            "1000000000, 1000000000, 1000000000, 1000000000, 0",
            "1000000000, 1000000000, 2000000000, 1000000000, 0",
            "2000000000, 1000000000, 1000000000, 1000000000, 648000",
            "1000000000, 1000000000, 2000000000, 2000000000, 162000",
            "2000000000, 2000000000, 1000000000, 1000000000, 810000",
            "2000000000, 2000000000, 1000000000, 3000000000, 486000",
            "2000000000, 2000000000, 3000000000, 1000000000, 1134000",
            "0, 0, 0, 0, 0"
    })
    void getDirectionTest(long baseX,
                          long baseY,
                          long targetX,
                          long targetY,
                          long expect) {

        long actualResult = inverseCalculator.getDirection(baseX,
                baseY,
                targetX,
                targetY);

        assertEquals(expect, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "10000000, 10005555, 5555",
            "10000155, 0, -10000155",
            "0, 0, 0"
    })
    void getElevationTest(long baseZ, long targetZ, long expect) {

        long actualResult = inverseCalculator.getElevation(baseZ, targetZ);

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
    void getTiltAngleTest(long baseX,
                          long baseY,
                          long baseZ,
                          long targetX,
                          long targetY,
                          long targetZ,
                          long expect) {

        long actualResult = inverseCalculator.getTiltAngle(baseX,
                baseY,
                baseZ,
                targetX,
                targetY,
                targetZ);

        assertEquals(expect, actualResult);
    }
}
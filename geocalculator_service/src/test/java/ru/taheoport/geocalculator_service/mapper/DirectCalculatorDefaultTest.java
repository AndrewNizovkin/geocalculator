package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DirectCalculatorDefault.class})
class DirectCalculatorDefaultTest {

    @Autowired
    private DirectCalculator directCalculator;

    @ParameterizedTest
    @CsvSource({"0, 0, 0, 0",
            "0, 0, 324000, 324000",
            "1260000, 0, 324000, 288000",
            "324000,1260000 , 324000, 684000"})
    void getDirectionalAngleTest(long landmarkDirectionalAngle,
                                 long landmarkDirection,
                                 long targetDirection,
                                 long expect) {

        long actual = directCalculator.getDirectionalAngle(
                landmarkDirectionalAngle,
                landmarkDirection,
                targetDirection);

        Assertions.assertEquals(expect, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0",
            "0, 100000, 0, 100000",
            "324000, 100000, 0, 0",
            "0, 100000, 7200, 99939",
            "0, 100000, -7200, 99939",
            "162000, 100000, 7200, 70668",
            "486000, 100000, 7200, -70668",
            "810000, 100000, 7200, -70668",
            "1134000, 100000, 7200, 70668"})
    void getDeltaXTest(
            long targetDirectionalAngle,
            long inclinedDistance,
            long tiltAngle,
            long expect) {

        long actualDeltaX = directCalculator.getDeltaX(
                targetDirectionalAngle,
                inclinedDistance,
                tiltAngle
        );

        Assertions.assertEquals(expect, actualDeltaX);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0",
            "0, 100000, 0, 0",
            "324000, 100000, 0, 100000",
            "324000, 100000, 7200, 99939",
            "324000, 100000, -7200, 99939",
            "162000, 100000, 7200, 70668",
            "486000, 100000, 7200, 70668",
            "810000, 100000, 7200, -70668",
            "1134000, 100000, 7200, -70668"})
    void getDeltaYTest(
            long targetDirectionalAngle,
            long inclinedDistance,
            long tiltAngle,
            long expect) {

        long actualDeltaY = directCalculator.getDeltaY(
                targetDirectionalAngle,
                inclinedDistance,
                tiltAngle
        );

        Assertions.assertEquals(expect, actualDeltaY);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "100000, 0, 0",
            "100000, 7200, 3490",
            "100000, -7200, -3490",
            "100000, 324000, 100000"
    })
    void getDeltaZTest(
            long inclinedDistance,
            long tiltAngle,
            long expect) {

        long actualDeltaZ = directCalculator.getDeltaZ(
                inclinedDistance,
                tiltAngle
        );

        Assertions.assertEquals(expect, actualDeltaZ);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "100000, 0, 100000",
            "100000, 3723, 99984",
            "100000, -3723, 99984",
            "100000, 1, 100000",
            "100000, 54061, 96585"
    })
    void getHorAngleTest(
            long inclinedDistance,
            long tiltAngle,
            long expectHorAngle) {

        long actualHorDistance = directCalculator.getHorDistance(
                inclinedDistance,
                tiltAngle
        );

        Assertions.assertEquals(expectHorAngle, actualHorDistance);
    }


    @ParameterizedTest
    @CsvSource({
            "0.124, 0",
            "-0.124, 0",
            "0.678, 1",
            "-0.678, -1",
            "0.546, 1",
            "-0.546, -1",
            "2.567, 3",
            "-2.567, -3"
    })
    void doubleToLongTest(
            double value,
            long expectValue
    ) {
        long actualValue = directCalculator.doubleToLong(value);

        assertEquals(expectValue, actualValue);
    }

}
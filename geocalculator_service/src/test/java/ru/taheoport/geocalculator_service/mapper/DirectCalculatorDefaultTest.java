package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
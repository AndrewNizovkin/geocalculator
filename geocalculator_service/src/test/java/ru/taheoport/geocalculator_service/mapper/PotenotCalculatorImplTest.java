package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PotenotCalculatorImpl.class})
class PotenotCalculatorImplTest {

    @Autowired
    private PotenotCalculator potenotCalculator;

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "1265914, 62348, 92434",
            "62348, 1265914, 1203566",
            "1142287, 587159, 740872",
            "587159, 1142287, 555128",
            "0, 1295999, 1295999",
            "1295999, 2, 3"
    })
    void difAngleTest(long first, long second, long expect) {

        long result = potenotCalculator.difAngle(first, second);

        Assertions.assertEquals(expect, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "4.384873098, 1.752451165, 6.137324263",
            "1, 2, 3",
            "4.384873098, 3.85727461, 1.958962401"
    })
    void addAngleTest(double first, double second, double expect) {

        double actual = potenotCalculator.addAngle(first, second);

        Assertions.assertEquals(expect, actual, 0.000000001);
    }

    @ParameterizedTest
    @CsvSource({
            "100000, 100000, 200000, 200000, 100000, 300000, 1.752451165, 3.85727461, 4.384873098",
            "100000, 100000, 200000, 200000, 100000, 300000, 0.791317738, 1.579809013, 2.653017829",
            "100000, 100000, 200000, 200000, 100000, 300000, 0.127026032, 0.151140665, 5.537963654",
            "-100000, -100000, -200000, -200000, -100000, -300000, 0.163236954, 0.754787027, 2.846627162"
    })
    void dirFromFirstTest(
            double firstX,
            double firstY,
            double secondX,
            double secondY,
            double thirdX,
            double thirdY,
            double firstAngle,
            double secondAngle,
            double expect
    ) {
        double actual = potenotCalculator.dirFromFirst(
                firstX,
                firstY,
                secondX,
                secondY,
                thirdX,
                thirdY,
                firstAngle,
                secondAngle
        );

        Assertions.assertEquals(expect, actual, 0.000001);

    }

    @ParameterizedTest
    @CsvSource({
            "100000, 100000, 200000, 200000, 4.384873098, 6.137324263, 137114"
    })
    void targetXTest(
            double firstX,
            double firstY,
            double secondX,
            double secondY,
            double dirFromFirst,
            double dirFromSecond,
            double expect
    ) {
        double actual = potenotCalculator.targetX(
        firstX,
        firstY,
        secondX,
        secondY,
        dirFromFirst,
        dirFromSecond
        );

        Assertions.assertEquals(expect, actual, 1);
    }

    @ParameterizedTest
    @CsvSource({
            "100000, 137114, 100000, 4.384873098, 209238"
    })
    void targetYTest(
            double firstY,
            double targetX,
            double firstX,
            double dirFromFirst,
            double expect
    ) {
        double actual = potenotCalculator.targetY(
                firstY,
                targetX,
                firstX,
                dirFromFirst
        );

        Assertions.assertEquals(expect, actual, 1);
    }

}
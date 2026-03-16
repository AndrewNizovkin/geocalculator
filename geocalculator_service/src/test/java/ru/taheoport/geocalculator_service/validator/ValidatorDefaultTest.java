package ru.taheoport.geocalculator_service.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ValidatorDefault.class)
class ValidatorDefaultTest {

    @Autowired
    private Validator validator;

    @ParameterizedTest
    @CsvSource({
            "0",
            "100000",
            "100.2345",
            "23323342.2121",
            ".100",
            ".234234"
    })
    void isValidPositiveNumberTrueTest(String value) {

        boolean actualResult = validator.isValidPositiveNumber(value);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "a",
            "/ / ",
            "aALsdf",
            "-.@,",
            "-#.234234",
            "-0.234234",
            "-100.234234"
    })
    void isPositiveNumberFalseTest(String value) {

        boolean actualResult = validator.isValidPositiveNumber(value);

        assertFalse(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "100000",
            "-10000",
            "100.2345",
            "-23323342.2121",
            ".100",
            "-.234234"
    })
    void isValidNumberTrueTest(String value) {

        boolean actualResult = validator.isValidNumber(value);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "a",
            "/ / ",
            "aALsdf",
            "-.@,",
            "-#.234234"
    })
    void isValidNumberFalseTest(String value) {

        boolean actualResult = validator.isValidNumber(value);

        assertFalse(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0000",
            "123.2345",
            "0.2355",
            "359.5959",
            "0.0000"
    })
    void isValidHorizontalAngleTrueTest(String value) {

        boolean actualResult = validator.isValidHorizontalAngle(value);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1,0000",
            "123.234543",
            "-123.2365",
            "-.2345",
            "-0.6355",
            "3df59.5959",
            "0",
            "-9.3456"
    })
    void isValidHorizontalAngleFalseTest(String value) {

        boolean actualResult = validator.isValidHorizontalAngle(value);

        assertFalse(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0000",
            "45.2345",
            "-45.2345",
            "-0.2345",
            "0.2355",
            "89.5959",
            "0.0000"
    })
    void isValidTiltAngleTrueTest(String value) {

        boolean actualResult = validator.isValidTiltAngle(value);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "1,0000",
            "123.234543",
            "-123.2365",
            "-.2345",
            "-0.6355",
            "3df59.5959",
            "0",
            "90.4545"
    })
    void isValidTiltAngleFalseTest(String value) {

        boolean actualResult = validator.isValidTiltAngle(value);

        assertFalse(actualResult);
    }

}
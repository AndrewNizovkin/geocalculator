package ru.taheoport.geocalculator_service.mapper;

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
            "-10000",
            "100.2345",
            "-23323342.2121",
            ".100",
            "-.234234"
    })
    void isDigitTrueTest(String number) {

        boolean actualResult = validator.isDigit(number);

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
    void isDigitFalseTest(String number) {

        boolean actualResult = validator.isDigit(number);

        assertFalse(actualResult);
    }


}
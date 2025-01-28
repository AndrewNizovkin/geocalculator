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

    @ParameterizedTest
    @CsvSource({
            "1.0000",
            "123.2345",
            "-123.2345",
            "-0.2345",
            "-0.2355",
            "359.5959",
            "0.0000"
    })
    void isDmsTrueTest(String angleValueDms) {

        boolean actualResult = validator.isDms(angleValueDms);

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
            "0"
    })
    void isDmsFalseTest(String angleValueDms) {

        boolean actualResult = validator.isDms(angleValueDms);

        assertFalse(actualResult);
    }


}
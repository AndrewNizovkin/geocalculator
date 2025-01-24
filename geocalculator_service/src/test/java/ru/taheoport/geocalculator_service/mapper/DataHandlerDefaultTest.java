package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DataHandlerDefault.class})
class DataHandlerDefaultTest {

    @Autowired
    private DataHandler dataHandler;

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

        boolean actualResult = dataHandler.isDigit(number);

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

        boolean actualResult = dataHandler.isDigit(number);

        assertFalse(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.0000",
            "59, 0.0059",
            "-59, -0.0059",
            "1295999, 359.5959",
            "1, 0.0001",
            "-1, -0.0001",
            "180, 0.0300",
            "18305, 5.0505",
            "-18305, -5.0505"
    })
    void secondToDmsTest(long valueAtSeconds, String expectValueAtDms) {

        String actualValueAtDms = dataHandler.secondsToDms(valueAtSeconds);

        assertNotNull(actualValueAtDms);
        assertEquals(expectValueAtDms, actualValueAtDms);
    }

    @ParameterizedTest
    @CsvSource({
            "0.0000, 0",
            "0.0059, 59",
            "-0.0059, -59",
            "359.5959, 1295999",
            "0.0001, 1",
            "-0.0001, -1",
            "0.0300, 180",
            "5.0505, 18305",
            "-5.0505, -18305"
    })
    void dmsToSecondsTest(String dmsValue, long expectSecondsValue) {

        long actualSecondsValue = dataHandler.dmsToSeconds(dmsValue);

        assertEquals(expectSecondsValue, actualSecondsValue);
    }
}
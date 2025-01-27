package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DataMapperDefault.class})
class DataMapperDefaultTest {

    @Autowired
    private DataMapper dataMapper;

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

        String actualValueAtDms = dataMapper.secondsToDms(valueAtSeconds);

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

        long actualSecondsValue = dataMapper.dmsToSeconds(dmsValue);

        assertEquals(expectSecondsValue, actualSecondsValue);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1000, 1000",
            "-1000, -1000",
            "'0,234', 0.234",
            "'-0,234', -0.234",
            "-0.234, -0.234"
    })
    void commaToPointTest(String value, String expectValue) {

        String actualValue = dataMapper.commaToPoint(value);

        assertNotNull(actualValue);
        assertEquals(expectValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.000",
            "1000, 1.000",
            "-1000, -1.000",
            "14578965412, 14578965.412",
            "-14578965412, -14578965.412"
    })
    void millimeterToMeterTest(long millimeters, String expectMeters) {

        String actualMeters = dataMapper.millimeterToMeter(millimeters);

        assertNotNull(actualMeters);
        assertEquals(expectMeters, actualMeters);

    }
}
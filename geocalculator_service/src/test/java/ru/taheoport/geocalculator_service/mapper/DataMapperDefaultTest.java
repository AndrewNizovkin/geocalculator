package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

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

    @ParameterizedTest
    @CsvSource({
            "0.000, 0",
            "1.000, 1000",
            "00001.000, 1000",
            "-1.000, -1000",
            "14578965.412, 14578965412",
            "-14578965.412, -14578965412"
    })
    void meterToMillimeterTest(String meters, long expectMillimeters) {

        long actualMillimeters = dataMapper.meterToMillimeter(meters);

        assertEquals(expectMillimeters, actualMillimeters);

    }


    @ParameterizedTest
    @CsvSource({
            "0.0000, 0",
            "0.0059, 0.00028604",
            "-0.0059, -0.00028604",
            "359.5959, 6.283180459",
            "0.0001, 0.000004848",
            "-0.0001, -0.000004848",
            "0.0300, 0.000872664",
            "5.0505, 0.088745144",
            "-5.0505, -0.088745144"
    })
    void dmsToRadiansTest(String value, double expectValue) {
        double actualValue = dataMapper.dmsToRadians(value);

        assertEquals(expectValue, actualValue, 0.000000001);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.0000",
            "0.00028604, 0.0059",
            "-0.00028604, -0.0059",
            "6.283180459, 359.5959",
            "0.000004848, 0.0001",
            "-0.000004848, -0.0001",
            "0.000872664, 0.0300",
            "0.088745144, 5.0505",
            "-0.088745144, -5.0505"
    })
    void radiansToDmsTest(double value, String expectValue) {
        String actualValue = dataMapper.radiansToDms(value);

        assertEquals(expectValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "00009901, 9901",
            "00000000, 0",
            "0000T144, T144"
    })
    void removeFirstZeroTest(
            String value,
            String expectValue
    ) {
        String actualValue = dataMapper.removeFirstZero(value);

        assertEquals(expectValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "00152081, 152081",
            "00000000, 0",
            "+00001536, +1536",
            "-00001536, -1536",
    })
    void leicaToMillimeterTest(String value, long expectValue) {

        long actualValue = dataMapper.leicaToMillimeter(value);

        assertEquals(expectValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "35959550, 1295995",
            "00000000, 0",
            "00009500, 590",
            "00000010, 1",

    })
    void leicaToDirection(String value, long expectValue) {

        long actualValue = dataMapper.leicaToDirection(value);

        assertEquals(expectValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "09009340, -574",
            "09009340, -574",
            "00000000, 0",
            "09129300, -5370",
            "08927300, 1950",
            "08956410, 199",
    })
    void leicaToTiltAngle(String value, long expectValue) {

        long actualValue = dataMapper.leicaToTiltAngle(value);

        assertEquals(expectValue, actualValue);
    }
}
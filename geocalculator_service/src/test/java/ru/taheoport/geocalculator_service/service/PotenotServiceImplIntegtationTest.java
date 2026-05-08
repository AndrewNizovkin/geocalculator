package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.mapper.DataMapperDefault;
import ru.taheoport.geocalculator_service.mapper.PotenotCalculatorImpl;
import ru.taheoport.geocalculator_service.mapper.PotenotTaskMapperImpl;
import ru.taheoport.geocalculator_service.validator.DataValidatorDefault;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        PotenotServiceImpl.class,
        PotenotTaskMapperImpl.class,
        PotenotCalculatorImpl.class,
        DataMapperDefault.class,
        DataValidatorDefault.class
})
class PotenotServiceImplIntegtationTest {

    @Autowired
    private PotenotService potenotService;

    @ParameterizedTest
    @CsvSource({
            "100.000, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425, 137.114, 209.238",
            "100.000, 100.000, 331.5847, 200.000, 200.000, 17.1908, 100.000, 300.000, 62.2946, 18.515, 143.314",
            "100.000, 100.000, 317.1807, 200.000, 200.000, 324.3448, 100.000, 300.000, 325.5742, -709.005, 846.436",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, -100.000, -300.000, 206.2045, 150.295, -176.044"
    })
    void getPotenotStringResponseTest(
            String firstX,
            String firstY,
            String firstDirection,
            String secondX,
            String secondY,
            String secondDirection,
            String thirdX,
            String thirdY,
            String thirdDirection,
            String expectX,
            String expectY
    ) {
        List<PotenotStringRequest> potenotStringRequestList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection)
        );
        String expectHeader = "OK";

        PotenotStringResponse actualResponse = potenotService.getPotenotStringResponse(potenotStringRequestList);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectHeader, actualResponse.getHeader());
        Assertions.assertEquals(expectX, actualResponse.getPointX());
        Assertions.assertEquals(expectY, actualResponse.getPointY());
    }

    @ParameterizedTest
    @CsvSource({
            "10bad00, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425, 'Invalid X of point 1!'",
            "1000.000, bad.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425, 'Invalid Y of point 1!'",
            "1000.000, 100.000, bad.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425, 'Invalid direction of point 1!'",
            "1000.00, 100.000, 251.1405, bad.000, 200.000, 351.3834, 100.000, 300.000, 112.1425, 'Invalid X of point 2!'",
            "1000.000, 100.000, 251.1405, 200.000, bad.000, 351.3834, 100.000, 300.000, 112.1425, 'Invalid Y of point 2!'",
            "1000.000, 100.000, 251.1405, 200.000, 200.000, bad.3834, 100.000, 300.000, 112.1425, 'Invalid direction of point 2!'",
            "1000.00, 100.000, 251.1405, 200.000, 200.000, 351.3834, bad.000, 300.000, 112.1425, 'Invalid X of point 3!'",
            "1000.000, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, bad.000, 112.1425, 'Invalid Y of point 3!'",
            "1000.000, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, bad.1425, 'Invalid direction of point 3!'",
    })
    void getPotenotStringResponseBadDataTest(
            String firstX,
            String firstY,
            String firstDirection,
            String secondX,
            String secondY,
            String secondDirection,
            String thirdX,
            String thirdY,
            String thirdDirection,
            String expectHeader
    ) {
        List<PotenotStringRequest> potenotStringRequestList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection)
        );
        String expectLinearValue = "0.000";

        PotenotStringResponse actualResponse = potenotService.getPotenotStringResponse(potenotStringRequestList);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectHeader, actualResponse.getHeader());
        Assertions.assertEquals(expectLinearValue, actualResponse.getPointX());
        Assertions.assertEquals(expectLinearValue, actualResponse.getPointY());
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "1",
            "4",
            "10"

    })
    void getPotenotStringResponseBadCountTest(int count) {
        List<PotenotStringRequest> potenotStringRequests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            potenotStringRequests.add(new PotenotStringRequest());
        }
        String expectHeader = "Invalid number of reference points!";
        String expectValue = "0.000";

        PotenotStringResponse actualResponse = potenotService.getPotenotStringResponse(potenotStringRequests);

        assertNotNull(actualResponse);
        assertEquals(expectHeader, actualResponse.getHeader());
        assertEquals(expectValue, actualResponse.getPointX());
        assertEquals(expectValue, actualResponse.getPointY());
    }

}
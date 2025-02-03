package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {
        PotenotTaskMapperImpl.class,
        PotenotCalculatorImpl.class,
        DataMapperDefault.class
})
class PotenotTaskMapperImplTest {

    @Autowired
    private PotenotTaskMapper potenotTaskMapper;

    @ParameterizedTest
    @CsvSource({
            "100000, 100000, 904445, 200000, 200000, 1265914, 100000, 300000, 404065, 137114, 209238",
            "100000, 100000, 1195127, 200000, 200000, 62348, 100000, 300000, 224986, 18515, 143314",
            "100000, 100000, 1142287, 200000, 200000, 1168488, 100000, 300000, 1173462, -709005, 846436",
            "-100000, -100000, 587159, -200000, -200000, 662082, -100000, -300000, 742845, 150295, -176044"
    })
    void solvePotenotTaskTest(
            long firstX,
            long firstY,
            long firstDirection,
            long secondX,
            long secondY,
            long secondDirection,
            long thirdX,
            long thirdY,
            long thirdDirection,
            long expectX,
            long expectY
    ) {
        List<PotenotTaskRequest> potenotTaskRequestList = List.of(
                new PotenotTaskRequest(firstX, firstY, firstDirection),
                new PotenotTaskRequest(secondX, secondY, secondDirection),
                new PotenotTaskRequest(thirdX, thirdY, thirdDirection)
        );

        PotenotTaskResponse actualResponse = potenotTaskMapper.solvePotenotTask(potenotTaskRequestList);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectX, actualResponse.getPointX(), 1);
        Assertions.assertEquals(expectY, actualResponse.getPointY(), 1);

    }

    @ParameterizedTest
    @CsvSource({
            "0.000, 0.000, 0.0000, 0, 0, 0",
            "2156654.456, 457896.999, 359.5959, 2156654456, 457896999, 1295999",
            "-2156654.456, -457896.999, -359.5959, -2156654456, -457896999, -1295999"
    })
    void toPotenotTaskRequestTest(
            String pointX,
            String pointY,
            String direction,
            long expectX,
            long expectY,
            long expectDirection
            ) {
        PotenotStringRequest potenotStringRequest = new PotenotStringRequest();
        potenotStringRequest.setPointX(pointX);
        potenotStringRequest.setPointY(pointY);
        potenotStringRequest.setDirection(direction);

        PotenotTaskRequest actualRequest = potenotTaskMapper.toPotenotTaskRequest(potenotStringRequest);

        assertNotNull(actualRequest);
        assertEquals(expectX, actualRequest.getPointX());
        assertEquals(expectY, actualRequest.getPointY());
        assertEquals(expectDirection, actualRequest.getDirection());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0.000, 0.000",
            "2156654456, 457896999, 2156654.456, 457896.999",
            "-2156654456, -457896999, -2156654.456, -457896.999"
    })
    void toPotenotStringResponseTest(
            long pointX,
            long pointY,
            String expectX,
            String expectY
    ) {
        PotenotTaskResponse potenotTaskResponse = new PotenotTaskResponse();
        potenotTaskResponse.setPointX(pointX);
        potenotTaskResponse.setPointY(pointY);

        PotenotStringResponse actualResponse = potenotTaskMapper.toPotenotStringResponse(potenotTaskResponse);

        assertNotNull(actualResponse);
        assertEquals(expectX, actualResponse.getPointX());
        assertEquals(expectY, actualResponse.getPointY());
    }

    @Test
    void toPotenotTaskRequestsTest() {
        List<PotenotTaskRequest> expectRequests = getPotenotTaskRequests();
        List<PotenotStringRequest> potenotStringRequests = getPotenotStringRequests();

        List<PotenotTaskRequest> actualRequests = potenotTaskMapper.toPotenotTaskRequests(potenotStringRequests);

        assertNotNull(actualRequests);
        assertEquals(expectRequests.size(), actualRequests.size());
        for (PotenotTaskRequest expectRequest: expectRequests) {
            boolean result = actualRequests.stream()
                    .filter(actualRequest -> actualRequest.getPointX() == expectRequest.getPointX())
                    .filter(actualRequest -> actualRequest.getPointY() == expectRequest.getPointY())
                    .anyMatch(actualRequest -> actualRequest.getDirection() == expectRequest.getDirection());
            assertTrue(result);
        }

    }

    /**
     * Gets list of PotenotStringResuest
     * @return List
     */
    private List<PotenotStringRequest> getPotenotStringRequests() {
        List<PotenotStringRequest> potenotStringRequests = new ArrayList<>();
        PotenotStringRequest potenotStringRequest = new PotenotStringRequest();
        potenotStringRequest.setPointX("0.000");
        potenotStringRequest.setPointY("0.000");
        potenotStringRequest.setDirection("0.0000");
        potenotStringRequests.add(potenotStringRequest);

        potenotStringRequest = new PotenotStringRequest();
        potenotStringRequest.setPointX("2156654.456");
        potenotStringRequest.setPointY("457896.999");
        potenotStringRequest.setDirection("359.5959");
        potenotStringRequests.add(potenotStringRequest);

        potenotStringRequest = new PotenotStringRequest();
        potenotStringRequest.setPointX("-2156654.456");
        potenotStringRequest.setPointY("-457896.999");
        potenotStringRequest.setDirection("-359.5959");
        potenotStringRequests.add(potenotStringRequest);

        return potenotStringRequests;
    }

    /**
     * Gets list of PotenotTaskResuest
     * @return List
     */
    private List<PotenotTaskRequest> getPotenotTaskRequests() {
        List<PotenotTaskRequest> potenotStringRequests = new ArrayList<>();
        PotenotTaskRequest potenotTaskRequest = new PotenotTaskRequest();
        potenotTaskRequest.setPointX(0);
        potenotTaskRequest.setPointY(0);
        potenotTaskRequest.setDirection(0);
        potenotStringRequests.add(potenotTaskRequest);

        potenotTaskRequest = new PotenotTaskRequest();
        potenotTaskRequest.setPointX(2156654456L);
        potenotTaskRequest.setPointY(457896999);
        potenotTaskRequest.setDirection(1295999);
        potenotStringRequests.add(potenotTaskRequest);

        potenotTaskRequest = new PotenotTaskRequest();
        potenotTaskRequest.setPointX(-2156654456L);
        potenotTaskRequest.setPointY(-457896999);
        potenotTaskRequest.setDirection(-1295999);
        potenotStringRequests.add(potenotTaskRequest);

        return potenotStringRequests;
    }

}
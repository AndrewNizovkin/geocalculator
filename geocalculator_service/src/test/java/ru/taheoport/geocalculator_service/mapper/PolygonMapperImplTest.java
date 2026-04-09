package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.PolygonStation;
import ru.taheoport.geocalculator_service.model.ValidResiduals;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;
import ru.taheoport.geocalculator_service.repository.PolygonRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        PolygonMapperImpl.class,
        PolygonRepositoryImpl.class,
        ValidResiduals.class,
        DataMapperDefault.class
})
class PolygonMapperImplTest {

    @Autowired
    private PolygonMapper polygonMapper;

    @Autowired
    private PolygonRepository polygonRepository;

    @Autowired
    private ValidResiduals validResiduals;

    @BeforeEach
    void clearPolygonRepository() {
        polygonRepository.clearAll();
    }

    @Test
    void polygonRequestToPolygonResidualsTest() {
        int expectElevation = 50;
        int expectAngle = 20;
        String expectAbsolute = "0.20";
        String expectRelative = "1:2000";
        List<String> polygonRequest = getTestPolygonRequest();

        boolean actualSuccess = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );


        assertTrue(actualSuccess);
        assertEquals(expectElevation, validResiduals.getValidElevation());
        assertEquals(expectAngle, validResiduals.getValidAngle());
        assertEquals(expectAbsolute, validResiduals.getValidAbsolute());
        assertEquals(expectRelative, validResiduals.getValidRelative());
    }

    @ParameterizedTest
    @CsvSource({
            "1008, 0, 0, 0, 456961430, 2261163707, 16930, true, 0",
            "1007, 457619, 68689, -286, 456988790, 2261202196, 17715, true, 1",
            "100, 636053, 64987, -174, 0, 0, 0, false, 2",
            "101, 355217, 57154, -928, 0, 0, 0, false, 3",
            "102, 623634, 33730, -560, 0, 0, 0, false, 4",
            "1003, 653188, 0, 0, 457129609, 2261111970, 15767, true, 5",
            "1004, 0, 0, 0, 457131018, 2261065036, 14661, true, 6"
    })
    void polygonRequestToPolygonRepositoryTest(
            String expectStationName,
            long expectHorAngle,
            long expectHorDistance,
            long expectElevation,
            long expectStationX,
            long expectStationY,
            long expectStationZ,
            boolean expectStatus,
            int stationIndex
    ) {
        List<String> polygonRequest = getTestPolygonRequest();
        int expectSize = 7;

        boolean actualSuccess = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );

        assertTrue(actualSuccess);
        int actualSize = polygonRepository.size();
        assertEquals(expectSize, actualSize);
        PolygonStation actualStation = polygonRepository.getStationById(stationIndex);
        assertEquals(expectStationName,actualStation.getStationName());
        assertEquals(expectHorAngle, actualStation.getHorAngle());
        assertEquals(expectHorDistance, actualStation.getHorDistance());
        assertEquals(expectElevation, actualStation.getElevation());
        assertEquals(expectStationX, actualStation.getStationX());
        assertEquals(expectStationY, actualStation.getStationY());
        assertEquals(expectStationZ, actualStation.getStationZ());
        assertEquals(expectStatus, actualStation.isStatus());
    }

    @Test
    void polygonRequestToPolygonBadRequestTest() {
        List<String> polygonBadRequest = new ArrayList<>();
        polygonBadRequest.add("asdfasf");

        boolean actualSuccess = polygonMapper.polygonRequestToPolygon(
                polygonBadRequest,
                polygonRepository,
                validResiduals
        );

        assertFalse(actualSuccess);
    }

    @Test
    void getErrorResponseTest() {
        String expectMessage = "Bad request";
        String expectTitle = "#error";
        int expectSize = 2;

        List<String> actualErrorResponse = polygonMapper.getErrorResponse(expectMessage);

        assertNotNull(actualErrorResponse);
        int actualSize = actualErrorResponse.size();
        assertEquals(expectSize, actualSize);

        String actualTitle = actualErrorResponse.get(0);
        String actualMessage = actualErrorResponse.get(1);

        assertEquals(expectTitle, actualTitle);
        assertEquals(expectMessage, actualMessage);
    }

    /**
     * Creates test polygonResponse
     * @return list of strings
     */
    private List<String> getTestPolygonRequest() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("1008 Not Not Not 456961.430 2261163.707 16.930");
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.715");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.609 2261111.97 15.767");
        polygonRequest.add("1004 Not Not Not 457131.018 2261065.036 14.661");

        return polygonRequest;
    }
}
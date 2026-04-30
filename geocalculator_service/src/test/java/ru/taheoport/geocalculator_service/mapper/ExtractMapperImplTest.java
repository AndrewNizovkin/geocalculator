package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.repository.ExtractRepository;
import ru.taheoport.geocalculator_service.repository.ExtractRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        ExtractMapperImpl.class,
        DataMapperDefault.class,
        ExtractRepositoryImpl.class
})
class ExtractMapperImplTest {

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private ExtractMapper extractMapper;

    @BeforeEach

    void clearRepository() {
        extractRepository.clearAll();
    }

    @ParameterizedTest
    @CsvSource({
            "101, 1550, T100, 68557, 1295994, 1061, 1600, 0, 0",
            "101, 1550, T102, 49147, 510490, -588, 1600, 0, 1",
            "101, 1550, 23, 46708, 512419, -462, 1600, 0, 2",
            "102, 1507, T101, 49133, 1295997, 1242, 1600, 1, 0",
            "102, 1507, T103, 33840, 567224, 948, 1600, 1, 1",
            "103, 1503, T102, 33836, 7, 275, 1600, 2, 0",
            "103, 1503, T104, 35381, 632794, 210, 1600, 2, 1",
            "104, 1522, T103, 35374, 1295996, 873, 1600, 3, 0",
            "104, 1522, T105, 75012, 624097, 90, 1600, 3, 1",
            "105, 1484, T104, 75020, 1295995, 473, 1600, 4, 0",
            "105, 1484, T106, 53068, 674105, 454, 1600, 4, 1"

    })
    void extractRequestToExtractionTest(
            String expectStationName,
            long expectStationHeight,
            String expectTargetName,
            long expectTargetInclinedDistance,
            long expectTargetDirection,
            long expectTargetTiltAngle,
            long expectTargetHeight,
            int indexExtraction,
            int indexMeasurement
    ) {
        boolean success = extractMapper.extractRequestToExtraction(getTestExtractRequest());
        assertTrue(success);

        Extraction actualExtraction = extractRepository.getExtractionById(indexExtraction);
        Measurement actualMeasurement = extractRepository.getMeasurementById(indexExtraction, indexMeasurement);

        assertNotNull(actualExtraction);
        assertNotNull(actualMeasurement);
        assertEquals(expectStationName, actualExtraction.getStationName());
        assertEquals(expectStationHeight, actualExtraction.getStationHeight());
        assertEquals(expectTargetName, actualMeasurement.getTargetName());
        assertEquals(expectTargetInclinedDistance, actualMeasurement.getTargetInclinedDistance());
        assertEquals(expectTargetDirection, actualMeasurement.getTargetDirection());
        assertEquals(expectTargetTiltAngle, actualMeasurement.getTargetTiltAngle());
        assertEquals(expectTargetHeight, actualMeasurement.getTargetHeight());

    }

    @Test
    void extractRequestToExtractionEmptyRequestTest() {
        List<String> badRequest = List.of();
        boolean success = extractMapper.extractRequestToExtraction(badRequest);

        assertFalse(success);
    }

    @Test
    void extractRequestToExtractionBadRequestTest() {
        List<String> badRequest = new ArrayList<>();
        badRequest.add("asdfasdf");
        badRequest.add("23243234");
        boolean success = extractMapper.extractRequestToExtraction(badRequest);

        assertFalse(success);
    }


    @Test
    void extractionToExtractResponse() {
    }

    @Test
    void getErrorResponseTest() {
        String expectMessage = "Bad request";
        String expectTitle = "#error";
        int expectSize = 2;

        List<String> actualErrorResponse = extractMapper.getErrorResponse(expectMessage);

        assertNotNull(actualErrorResponse);
        int actualSize = actualErrorResponse.size();
        assertEquals(expectSize, actualSize);

        String actualTitle = actualErrorResponse.get(0);
        String actualMessage = actualErrorResponse.get(1);

        assertEquals(expectTitle, actualTitle);
        assertEquals(expectMessage, actualMessage);
    }

    /**
     * Gets test extract request
     * @return list of strings in tah-format
     */
    private List<String> getTestExtractRequest() {
        List<String> extractRequest = new ArrayList<>();
        extractRequest.add("101 478650.714 2297071.740 10.930 1.550 100 478660.283 2297003.862");
        extractRequest.add("102 478675.411 2297114.212 10.733 1.507 101 478650.714 2297071.740");
        extractRequest.add("103 478702.294 2297134.754 10.789 1.503 102 478675.411 2297114.212");
        extractRequest.add("104 478731.901 2297154.110 10.722 1.522 103 478702.294 2297134.754");
        extractRequest.add("105 478799.005 2297187.623 10.668 1.484 104 478731.901 2297154.110");
        extractRequest.add("//");
        extractRequest.add("T100 68.557 359.5954 0.1741 1.600 0");
        extractRequest.add("T102 49.147 141.4810 -0.0948 1.600 0");
        extractRequest.add("23 46.708 142.2019 -0.0742 1.600 0");
        extractRequest.add("//");
        extractRequest.add("T101 49.133 359.5957 0.2042 1.600 1");
        extractRequest.add("T103 33.840 157.3344 0.1548 1.600 1");
        extractRequest.add("//");
        extractRequest.add("T102 33.836 0.0007 0.0435 1.600 2");
        extractRequest.add("T104 35.381 175.4634 0.0330 1.600 2");
        extractRequest.add("//");
        extractRequest.add("T103 35.374 359.5956 0.1433 1.600 3");
        extractRequest.add("T105 75.012 173.2137 0.0130 1.600 3");
        extractRequest.add("//");
        extractRequest.add("T104 75.020 359.5955 0.0753 1.600 4");
        extractRequest.add("T106 53.068 187.1505 0.0734 1.600 4");
        extractRequest.add("//");

        return extractRequest;
    }
}
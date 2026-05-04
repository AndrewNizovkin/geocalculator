package ru.taheoport.geocalculator_service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.PolygonStation;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ExtractRepositoryImpl.class)
class ExtractRepositoryImplTest {

    @Autowired
    private ExtractRepository extractRepository;

    @BeforeEach
    void clearRepository() {
        extractRepository.clearAll();
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "5",
            "10"
    })
    void sizeTest(int expectSize) {
        for (int i = 0; i < expectSize; i++) extractRepository.addNewExtraction();

        int actualSize = extractRepository.size();

        assertEquals(expectSize, actualSize);
    }

    @Test
    void addNewExtractionTest() {
        Extraction expectExtraction = fillTestExtraction(extractRepository.addNewExtraction());

        Extraction actualExtraction = extractRepository.getExtractionById(extractRepository.size() - 1);
        assertNotNull(actualExtraction);
        assertEquals(expectExtraction.getStationName(), actualExtraction.getStationName());
        assertEquals(expectExtraction.getStationHeight(), actualExtraction.getStationHeight());
        assertEquals(expectExtraction.getStationName(), actualExtraction.getStationName());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "3, 0",
            "3, 3"
    })
    void getExtractionByIdTest(int before, int after) {
        for (int i = 0; i < before; i++) {
            extractRepository.addNewExtraction();
        }
        Extraction expectExtraction = fillTestExtraction(extractRepository.addNewExtraction());
        int actualIndex = extractRepository.size() - 1;
        for (int i = 0; i < after; i++) {
            extractRepository.addNewExtraction();
        }

        Extraction actualExtraction = extractRepository.getExtractionById(actualIndex);

        assertNotNull(actualExtraction);
        assertEquals(expectExtraction.getStationName(), actualExtraction.getStationName());
        assertEquals(expectExtraction.getStationHeight(), actualExtraction.getStationHeight());
        assertEquals(expectExtraction.getStationName(), actualExtraction.getStationName());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 3",
            "3, 4",
            "3, -1"
    })
    void getExtractionByIdBadID(
            int before,
            int badIndex
    ) {
        for (int i = 0; i < before; i++) {
            extractRepository.addNewExtraction();
        }

        Extraction actualExtraction = extractRepository.getExtractionById(badIndex);

        assertNull(actualExtraction);
    }

    @Test
    void addNewMeasurementTest() {
        Extraction extraction = extractRepository.addNewExtraction();
        int actualIndexExtraction = extractRepository.size() - 1;
        Measurement expectMeasurement = extractRepository.addNewMeasurement(actualIndexExtraction);
        fillTestMeasurement(expectMeasurement);


        Measurement actualMeasurement = extraction.getMeasurements().getLast();

        assertNotNull(actualMeasurement);
        assertEquals(expectMeasurement.getTargetName(), actualMeasurement.getTargetName());
        assertEquals(expectMeasurement.getTargetDirection(), actualMeasurement.getTargetDirection());
        assertEquals(expectMeasurement.getTargetInclinedDistance(), actualMeasurement.getTargetInclinedDistance());
        assertEquals(expectMeasurement.getTargetTiltAngle(), actualMeasurement.getTargetTiltAngle());
        assertEquals(expectMeasurement.getTargetHeight(), actualMeasurement.getTargetHeight());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 3",
            "3, 4",
            "3, -1"
    })
    void addNewMeasurementBadExtractionIndexTest(
            int before,
            int badIndex
    ) {
        for (int i = 0; i < before; i++) {
            extractRepository.addNewExtraction();
        }

        Measurement actualMeasurement = extractRepository.addNewMeasurement(badIndex);

        assertNull(actualMeasurement);
    }


    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "3, 0",
            "3, 3"
    })
    void getMeasurementByIdTest(int before, int after) {
        Extraction extraction = extractRepository.addNewExtraction();
        int indexExtraction = extractRepository.size() - 1;
        for (int i = 0; i < before; i++) {
            extractRepository.addNewMeasurement(indexExtraction);
        }
        Measurement expectMeasurement = fillTestMeasurement(extractRepository.addNewMeasurement(indexExtraction));
        int indexMeasurement = extraction.getMeasurements().size() - 1;
        for (int i = 0; i < after; i++) {
            extractRepository.addNewExtraction();
        }

        Measurement actualMeasurement = extractRepository.getMeasurementById(indexExtraction, indexMeasurement);

        assertNotNull(actualMeasurement);
        assertEquals(expectMeasurement.getTargetName(), actualMeasurement.getTargetName());
        assertEquals(expectMeasurement.getTargetDirection(), actualMeasurement.getTargetDirection());
        assertEquals(expectMeasurement.getTargetInclinedDistance(), actualMeasurement.getTargetInclinedDistance());
        assertEquals(expectMeasurement.getTargetTiltAngle(), actualMeasurement.getTargetTiltAngle());
        assertEquals(expectMeasurement.getTargetHeight(), actualMeasurement.getTargetHeight());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 3",
            "3, 4",
            "3, -1"
    })
    void getMeasurementByIdBadExtractionIndexTest(
            int before,
            int badIndex
    ) {
        for (int i = 0; i < before; i++) {
            extractRepository.addNewExtraction();
            extractRepository.addNewMeasurement(extractRepository.size() - 1);
        }

        Measurement actualMeasurement = extractRepository.getMeasurementById(badIndex, 0);

        assertNull(actualMeasurement);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 3",
            "3, 4",
            "3, -1"
    })
    void getMeasurementByIdBadMeasurementIndexTest(
            int before,
            int badIndex
    ) {
        extractRepository.addNewExtraction();
        int indexExtraction = extractRepository.size() - 1;
        for (int i = 0; i < before; i++) {
            extractRepository.addNewMeasurement(indexExtraction);
        }

        Measurement actualMeasurement = extractRepository.getMeasurementById(indexExtraction, badIndex);

        assertNull(actualMeasurement);
    }

    /**
     * Fills the Extraction instance with test data
     * @param extraction instance of Extraction
     * @return extraction
     */
    private Extraction fillTestExtraction(Extraction extraction) {
        extraction.setStationName("testStation");
        extraction.setStationHeight(158998);

        return extraction;
    }

    /**
     * Fills the Measurement instance with test data
     * @param measurement instance of Measurement
     * @return measurement
     */
    private Measurement fillTestMeasurement(Measurement measurement) {
        measurement.setTargetName("test-name");
        measurement.setTargetDirection(120);
        measurement.setTargetInclinedDistance(76897);
        measurement.setTargetTiltAngle(-78);
        measurement.setTargetHeight(1600);

        return measurement;
    }
}
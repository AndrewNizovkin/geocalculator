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
        assertEquals(expectExtraction.getHorAngle(), actualExtraction.getHorAngle());
        assertEquals(expectExtraction.getDirectHorDistance(), actualExtraction.getDirectHorDistance());
        assertEquals(expectExtraction.getDirectElevation(), actualExtraction.getDirectElevation());
        assertEquals(expectExtraction.getInverseHorDistance(), actualExtraction.getInverseHorDistance());
        assertEquals(expectExtraction.getInverseElevation(), actualExtraction.getInverseElevation());
        assertEquals(expectExtraction.getAverageHorDistance(), actualExtraction.getAverageHorDistance());
        assertEquals(expectExtraction.getAverageElevation(), actualExtraction.getAverageElevation());
        assertEquals(expectExtraction.getDeltaHorDistance(), actualExtraction.getDeltaHorDistance());
        assertEquals(expectExtraction.getDeltaElevation(), actualExtraction.getDeltaElevation());
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
        assertEquals(expectExtraction.getHorAngle(), actualExtraction.getHorAngle());
        assertEquals(expectExtraction.getDirectHorDistance(), actualExtraction.getDirectHorDistance());
        assertEquals(expectExtraction.getDirectElevation(), actualExtraction.getDirectElevation());
        assertEquals(expectExtraction.getInverseHorDistance(), actualExtraction.getInverseHorDistance());
        assertEquals(expectExtraction.getInverseElevation(), actualExtraction.getInverseElevation());
        assertEquals(expectExtraction.getAverageHorDistance(), actualExtraction.getAverageHorDistance());
        assertEquals(expectExtraction.getAverageElevation(), actualExtraction.getAverageElevation());
        assertEquals(expectExtraction.getDeltaHorDistance(), actualExtraction.getDeltaHorDistance());
        assertEquals(expectExtraction.getDeltaElevation(), actualExtraction.getDeltaElevation());

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

    }

    @Test
    void addNewMeasurementBadExtractionIndexTest() {

    }


    @Test
    void getMeasurementByIdTest() {

    }

    @Test
    void getMeasurementByIdBadExtractionIndexTest() {

    }

    @Test
    void getMeasurementByIdBadMeasurementIndexTest() {

    }


    /**
     * Fills the Extraction instance with test data
     * @param extraction instance of Extraction
     * @return extraction
     */
    private Extraction fillTestExtraction(Extraction extraction) {
        extraction.setStationName("testStation");
        extraction.setStationHeight(158998);
        extraction.setHorAngle(324898);
        extraction.setDirectHorDistance(345677);
        extraction.setDirectElevation(200);
        extraction.setInverseHorDistance(345678);
        extraction.setInverseElevation(-205);
        extraction.setAverageHorDistance(345678);
        extraction.setAverageElevation(203);
        extraction.setDeltaHorDistance(1);
        extraction.setDeltaElevation(5);

        return extraction;
    }

    /**
     * Fills the Measurement instance with test data
     * @param measurement instance of Mesurement
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
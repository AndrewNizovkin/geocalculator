package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Solution;
import ru.taheoport.geocalculator_service.repository.ExtractRepositoryImpl;
import ru.taheoport.geocalculator_service.repository.SolutionRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepositoryImpl;
import ru.taheoport.geocalculator_service.validator.DataValidatorDefault;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        ExtractRepositoryImpl.class,
        SolutionRepositoryImpl.class,
        ExtractCalculatorImpl.class,
        DirectCalculatorDefault.class,
        PotenotCalculatorImpl.class,
        ExtractMapperImpl.class,
        DataMapperDefault.class,
        DataValidatorDefault.class
})
class ExtractCalculatorImplTest {

    @Autowired
    private ExtractMapper extractMapper;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private ExtractCalculator extractCalculator;

    @ParameterizedTest
    @CsvSource({
            "T100, 0, 0, 68556, 303, 68556, -303, 0, 0, 0",
            "101, 49147, -190, 49132, 203, 49140, -197, 15, 13, 1",
            "102, 33840, 63, 33836, -52, 33838, 58, 4, 11, 2",
            "103, 35381, -61, 35374, 72, 35378, -67, 7, 11, 3",
            "104, 75012, -45, 75020, 56, 75016, -51, -8, 11, 4",
            "105, 53068, 1, 0, 0, 53068, 1, 0, 0, 5",
            "T106, 0, 0, 0, 0, 0, 0, 0, 0, 6"
    })
    void extractionToSolutionTest(
            String expectStationName,
            long expectDirectHorDistance,
            long expectDirectElevation,
            long expectInverseHorDistance,
            long expectInverseElevation,
            long expectAverageDistance,
            long expectAverageElevation,
            long expectDeltaDistance,
            long expectDeltaElevation,
            int indexSolution
    ) {
        extractMapper.extractRequestToExtraction(getTestExtractRequest());
        extractCalculator.ExtractionToSolution();

        Solution actualSolution = solutionRepository.getSolutionById(indexSolution);

        assertNotNull(actualSolution);
        assertEquals(expectStationName, actualSolution.getStationName());
        assertEquals(expectDirectHorDistance, actualSolution.getDirectHorDistance());
        assertEquals(expectDirectElevation, actualSolution.getDirectElevation());
        assertEquals(expectInverseHorDistance, actualSolution.getInverseHorDistance());
        assertEquals(expectInverseElevation, actualSolution.getInverseElevation());
        assertEquals(expectAverageDistance, actualSolution.getAverageHorDistance());
        assertEquals(expectAverageElevation, actualSolution.getAverageElevation());
        assertEquals(expectDeltaDistance, actualSolution.getDeltaHorDistance());
        assertEquals(expectDeltaElevation,actualSolution.getDeltaElevation());
    }

    @Test
    void createSolutionsTest() {
        List<String> expectNames = getExpectStationNames();
        int expectSize = expectNames.size();
        extractMapper.extractRequestToExtraction(getTestExtractRequest());

        extractCalculator.createSolutions();

        int actualSize = solutionRepository.size();

        assertEquals(expectSize, actualSize);
        for (int i = 0; i < expectSize; i++) {
            String expectStationName = expectNames.get(i);
            String actualStationName = solutionRepository.getSolutionById(i).getStationName();

            assertEquals(expectStationName, actualStationName);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "49147, -190, 1",
            "33840, 63, 2",
            "35381, -61, 3",
            "75012, -45, 4",
            "53068, 1, 5",
            "0, 0, 6"
    })
    void setDirectValuesTest(
            long expectDirectHorDistance,
            long expectDirectElevation,
            int indexSolution
    ) {
        extractMapper.extractRequestToExtraction(getTestExtractRequest());
        extractCalculator.createSolutions();
        extractCalculator.setDirectValues();

        long actualDirectHorDistance = solutionRepository.getSolutionById(indexSolution).getDirectHorDistance();
        long actualDirectElevation = solutionRepository.getSolutionById(indexSolution).getDirectElevation();

        assertEquals(expectDirectHorDistance, actualDirectHorDistance);
        assertEquals(expectDirectElevation, actualDirectElevation);
    }

    @ParameterizedTest
    @CsvSource({
            "68556, 303, 0",
            "49132, 203, 1",
            "33836, -52, 2",
            "35374, 72, 3",
            "75020, 56, 4",
            "0, 0, 5",
            "0, 0, 6"
    })
    void setInverseValuesTest(
            long expectInverseHorDistance,
            long expectInverseElevation,
            int indexSolution
    ) {
        extractMapper.extractRequestToExtraction(getTestExtractRequest());
        extractCalculator.createSolutions();
        extractCalculator.setInverseValues();

        long actualInverseHorDistance = solutionRepository.getSolutionById(indexSolution).getInverseHorDistance();
        long actualInverseElevation = solutionRepository.getSolutionById(indexSolution).getInverseElevation();

        assertEquals(expectInverseHorDistance, actualInverseHorDistance);
        assertEquals(expectInverseElevation, actualInverseElevation);
    }

    @ParameterizedTest
    @CsvSource({
            "68556, -303, 0, 0, 0",
            "49140, -197, 15, 13, 1",
            "33838, 58, 4, 11, 2",
            "35378, -67, 7, 11, 3",
            "75016, -51, -8, 11, 4",
            "53068, 1, 0, 0, 5",
            "0, 0, 0, 0, 6"
    })
    void setAverageValuesTest(
            long expectAverageDistance,
            long expectAverageElevation,
            long expectDeltaDistance,
            long expectDeltaElevation,
            int indexSolution
    ) {
        extractMapper.extractRequestToExtraction(getTestExtractRequest());
        extractCalculator.createSolutions();
        extractCalculator.setDirectValues();
        extractCalculator.setInverseValues();
        extractCalculator.setAverageValues();

        Solution actualSolution = solutionRepository.getSolutionById(indexSolution);

        assertNotNull(actualSolution);
        assertEquals(expectAverageDistance, actualSolution.getAverageHorDistance());
        assertEquals(expectAverageElevation, actualSolution.getAverageElevation());
        assertEquals(expectDeltaDistance, actualSolution.getDeltaHorDistance());
        assertEquals(expectDeltaElevation, actualSolution.getDeltaElevation());
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

    /**
     * Gets expect name list
     * @return List of strings
     */
    private List<String> getExpectStationNames() {
        return List.of("T100", "101", "102", "103", "104", "105", "T106");
    }

}
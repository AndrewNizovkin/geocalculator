package ru.taheoport.geocalculator_service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Solution;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SolutionRepositoryImpl.class)
class SolutionRepositoryImplTest {

    @Autowired
    private SolutionRepository solutionRepository;

    @BeforeEach
    void clearSolutionRepository() {
        solutionRepository.clearAll();
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "5",
            "10"
    })
    void sizeTest(int expectSize) {
        for (int i = 0; i < expectSize; i++) solutionRepository.addNewSolution();

        int actualSize = solutionRepository.size();

        assertEquals(expectSize, actualSize);
    }

    @Test
    void addNewSolutionTest() {
        Solution expectSolution = fillTestSolution(solutionRepository.addNewSolution());

        Solution actualSolution = solutionRepository.getSolutionById(solutionRepository.size() - 1);
        assertNotNull(actualSolution);
        assertEquals(expectSolution.getStationName(), actualSolution.getStationName());
        assertEquals(expectSolution.getStationName(), actualSolution.getStationName());
        assertEquals(expectSolution.getHorAngle(), actualSolution.getHorAngle());
        assertEquals(expectSolution.getDirectHorDistance(), actualSolution.getDirectHorDistance());
        assertEquals(expectSolution.getDirectElevation(), actualSolution.getDirectElevation());
        assertEquals(expectSolution.getInverseHorDistance(), actualSolution.getInverseHorDistance());
        assertEquals(expectSolution.getInverseElevation(), actualSolution.getInverseElevation());
        assertEquals(expectSolution.getAverageHorDistance(), actualSolution.getAverageHorDistance());
        assertEquals(expectSolution.getAverageElevation(), actualSolution.getAverageElevation());
        assertEquals(expectSolution.getDeltaHorDistance(), actualSolution.getDeltaHorDistance());
        assertEquals(expectSolution.getDeltaElevation(), actualSolution.getDeltaElevation());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "3, 0",
            "3, 3"
    })
    void getSolutionByIdTest(int before, int after) {

        for (int i = 0; i < before; i++) {
            solutionRepository.addNewSolution();
        }
        Solution expectSolution = fillTestSolution(solutionRepository.addNewSolution());
        int actualIndex = solutionRepository.size() - 1;
        for (int i = 0; i < after; i++) {
            solutionRepository.addNewSolution();
        }

        Solution actualSolution = solutionRepository.getSolutionById(actualIndex);
        assertNotNull(actualSolution);
        assertEquals(expectSolution.getStationName(), actualSolution.getStationName());
        assertEquals(expectSolution.getStationName(), actualSolution.getStationName());
        assertEquals(expectSolution.getHorAngle(), actualSolution.getHorAngle());
        assertEquals(expectSolution.getDirectHorDistance(), actualSolution.getDirectHorDistance());
        assertEquals(expectSolution.getDirectElevation(), actualSolution.getDirectElevation());
        assertEquals(expectSolution.getInverseHorDistance(), actualSolution.getInverseHorDistance());
        assertEquals(expectSolution.getInverseElevation(), actualSolution.getInverseElevation());
        assertEquals(expectSolution.getAverageHorDistance(), actualSolution.getAverageHorDistance(), 0.00001);
        assertEquals(expectSolution.getAverageElevation(), actualSolution.getAverageElevation(), 0.000001);
        assertEquals(expectSolution.getDeltaHorDistance(), actualSolution.getDeltaHorDistance());
        assertEquals(expectSolution.getDeltaElevation(), actualSolution.getDeltaElevation());

    }


    @Test
    void getSolutionById() {
    }

    /**
     * Fills the Solution instance with test data
     * @param solution instance of Extraction
     * @return Solution
     */
    private Solution fillTestSolution(Solution solution) {
        solution.setStationName("testStation");
        solution.setHorAngle(324898);
        solution.setDirectHorDistance(345677);
        solution.setDirectElevation(200);
        solution.setInverseHorDistance(345678);
        solution.setInverseElevation(-205);
        solution.setAverageHorDistance(345678.0233);
        solution.setAverageElevation(203.45667);
        solution.setDeltaHorDistance(1);
        solution.setDeltaElevation(5);

        return solution;
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 3",
            "3, 4",
            "3, -1"
    })
    void getSolutionByIdBadID(
            int before,
            int badIndex
    ) {
        for (int i = 0; i < before; i++) {
            solutionRepository.addNewSolution();
        }

        Solution actualSolution = solutionRepository.getSolutionById(badIndex);

        assertNull(actualSolution);
    }


}
package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.repository.ExtractRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepository;

/**
 * This interface defines methods for
 * extraction geodetic polygon from survey data
 */
@Component
@RequiredArgsConstructor
public class ExtractCalculatorImpl implements ExtractCalculator{

    private final ExtractRepository extractRepository;

    private final SolutionRepository solutionRepository;

    private final PotenotCalculator potenotCalculator;

    private final DirectCalculator directCalculator;

    /**
     * Converts extractions to solutions
     */
    @Override
    public void ExtractionToSolution() {
        createSolutions();

    }

    /**
     * Creates new solution collection
     */
    @Override
    public void createSolutions() {
        solutionRepository.clearAll();
        solutionRepository.addNewSolution();
        for (int i = 0; i < extractRepository.size(); i++) {
            solutionRepository.addNewSolution().setStationName(extractRepository.getExtractionById(i).getStationName());
        }
        solutionRepository.addNewSolution();

        solutionRepository.getSolutionById(0).setStationName(
                extractRepository.getMeasurementById(
                        0, 0).getTargetName());

        solutionRepository.getSolutionById(solutionRepository.size() - 1).setStationName(
                extractRepository.getMeasurementById(
                        extractRepository.size() - 1, 1).getTargetName()
        );
    }

    /**
     * Sets direct values of horizontal distance and elevation
     * for specified range of extractions
     *
     * @param start start index of extraction
     * @param end   end index of extraction
     */
    @Override
    public void setDirectValues(int start, int end) {

    }

    /**
     * Sets inverse values of horizontal distance and elevation
     * for specified range of extractions
     *
     * @param start start index of extraction
     * @param end   end index of extraction
     */
    @Override
    public void setInverseValues(int start, int end) {

    }

    /**
     * Sets average values of horizontal distance and elevation
     * for specified range of solutions
     *
     * @param start start index of extraction
     * @param end   end index of extraction
     */
    @Override
    public void setAverageValues(int start, int end) {

    }
}

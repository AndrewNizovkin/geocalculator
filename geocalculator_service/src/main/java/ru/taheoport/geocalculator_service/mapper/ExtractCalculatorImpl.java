package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.Solution;
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
        solutionRepository.clearAll();
        createSolutions();
        setDirectValues();
        setInverseValues();
        setAverageValues();
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
     */
    @Override
    public void setDirectValues() {
        Extraction station;
        Measurement backMeasurement;
        Measurement frontMeasurement;
        Solution solution;

        for (int i = 0; i < extractRepository.size(); i++) {
            station = extractRepository.getExtractionById(i);
            backMeasurement = extractRepository.getMeasurementById(i, 0);
            frontMeasurement = extractRepository.getMeasurementById(i, 1);

            solution = solutionRepository.getSolutionById(i + 1);

            solution.setHorAngle(potenotCalculator.difAngle(
                    backMeasurement.getTargetDirection(),
                    frontMeasurement.getTargetDirection()
            ));

            solution.setDirectHorDistance(directCalculator.getHorDistance(
                    frontMeasurement.getTargetInclinedDistance(),
                    frontMeasurement.getTargetTiltAngle()
            ));

            solution.setDirectElevation(directCalculator.getDeltaZ(
                    frontMeasurement.getTargetInclinedDistance(),
                    frontMeasurement.getTargetTiltAngle()
            ) + station.getStationHeight() - frontMeasurement.getTargetHeight());

        }


    }

    /**
     * Sets inverse values of horizontal distance and elevation
     * for specified range of extractions
     */
    @Override
    public void setInverseValues() {
        Extraction station;
        Measurement inverseMeasurement;
        Solution solution;

        for (int i = 0; i < extractRepository.size(); i++) {
            station = extractRepository.getExtractionById(i);
            inverseMeasurement = extractRepository.getMeasurementById(i, 0);
            solution = solutionRepository.getSolutionById(i);


            solution.setInverseHorDistance(directCalculator.getHorDistance(
                    inverseMeasurement.getTargetInclinedDistance(),
                    inverseMeasurement.getTargetTiltAngle()
            ));

            solution.setInverseElevation(directCalculator.getDeltaZ(
                    inverseMeasurement.getTargetInclinedDistance(),
                    inverseMeasurement.getTargetTiltAngle()
            ) + station.getStationHeight() - inverseMeasurement.getTargetHeight());
        }
    }

    /**
     * Sets average values of horizontal distance and elevation
     * for specified range of solutions
     */
    @Override
    public void setAverageValues() {
        Solution solution;

        for (int i = 1; i < solutionRepository.size() - 2; i++) {
            solution = solutionRepository.getSolutionById(i);

            solution.setAverageHorDistance(
                    directCalculator.doubleToLong(
                            (double) (solution.getDirectHorDistance() + solution.getInverseHorDistance()) / 2)
            );

            solution.setAverageElevation(
                    directCalculator.doubleToLong(
                            (double) (solution.getDirectElevation() - solution.getInverseElevation()) / 2)
            );

            solution.setDeltaHorDistance(solution.getDirectHorDistance() - solution.getInverseHorDistance());

            solution.setDeltaElevation(solution.getDirectElevation() + solution.getInverseElevation());

        }

        solution = solutionRepository.getSolutionById(0);
        solution.setAverageHorDistance(solution.getInverseHorDistance());
        solution.setAverageElevation(-1 * solution.getInverseElevation());

        solution = solutionRepository.getSolutionById(solutionRepository.size() - 2);
        solution.setAverageHorDistance(solution.getDirectHorDistance());
        solution.setAverageElevation(solution.getDirectElevation());
    }
}

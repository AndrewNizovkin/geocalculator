package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.DirectCalculator;
import ru.taheoport.geocalculator_service.mapper.ExtractMapper;
import ru.taheoport.geocalculator_service.mapper.InverseCalculator;
import ru.taheoport.geocalculator_service.mapper.PotenotCalculator;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.Solution;
import ru.taheoport.geocalculator_service.repository.ExtractRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepositoryImpl;

import java.util.List;

/**
 * Provides methods for extracting polygon from survey data
 */
@Service
@RequiredArgsConstructor
public class ExtractServiceImpl implements ExtractService{

    private final PotenotCalculator potenotCalculator;

    private final DirectCalculator directCalculator;

    private final ExtractRepository extractRepository;

    private final SolutionRepository solutionRepository;

    private final ExtractMapper extractMapper;

    /**
     * Gets general report of polygon extraction
     *
     * @param extractRequest List of strings
     * @return List of strings
     */
    @Override
    public List<String> getExtractReports(List<String> extractRequest) {

        boolean success = extractMapper.extractRequestToExtraction(extractRequest);
        if (!success) return extractMapper.getErrorResponse("Bad request!");

        extractionToSolution();

        return extractMapper.solutionToExtractResponse();
    }

    /**
     * Processes extraction and creates solution
     */
    @Override
    public void extractionToSolution() {
        solutionRepository.clearAll();
        Extraction station;
        Extraction frontPoint;
        Measurement backMeasurement;
        Measurement directMeasurement;
        Measurement inverseMeasurement;
        Solution solution;

        solutionRepository.addNewSolution();

        for (int i = 0; i < extractRepository.size() - 1; i++) {
            station = extractRepository.getExtractionById(i);
            frontPoint = extractRepository.getExtractionById(i + 1);
            backMeasurement = extractRepository.getMeasurementById(i, 0);
            directMeasurement = extractRepository.getMeasurementById(i, 1);
            inverseMeasurement = extractRepository.getMeasurementById(i + 1, 0);
            solution = solutionRepository.addNewSolution();


            solution.setStationName(station.getStationName());

            solution.setHorAngle(potenotCalculator.difAngle(
                    backMeasurement.getTargetDirection(),
                    directMeasurement.getTargetDirection()
            ));

            solution.setDirectHorDistance(directCalculator.getHorDistance(
                    directMeasurement.getTargetInclinedDistance(),
                    directMeasurement.getTargetTiltAngle()
            ));

            solution.setDirectElevation(directCalculator.getDeltaZ(
                    directMeasurement.getTargetInclinedDistance(),
                    directMeasurement.getTargetTiltAngle()
            ) + station.getStationHeight() - directMeasurement.getTargetHeight());

            solution.setInverseHorDistance(directCalculator.getHorDistance(
                    inverseMeasurement.getTargetInclinedDistance(),
                    inverseMeasurement.getTargetTiltAngle()
            ));

            solution.setInverseElevation(directCalculator.getDeltaZ(
                    inverseMeasurement.getTargetInclinedDistance(),
                    inverseMeasurement.getTargetTiltAngle()
            ) + frontPoint.getStationHeight() - inverseMeasurement.getTargetHeight());

            solution.setAverageHorDistance((double) (solution.getDirectHorDistance() + solution.getInverseHorDistance()) / 2);

            solution.setAverageElevation((double) (solution.getDirectElevation() - solution.getInverseElevation()) / 2);

            solution.setDeltaHorDistance(solution.getDirectHorDistance() - solution.getInverseHorDistance());

            solution.setDeltaElevation(solution.getDirectElevation() + solution.getInverseElevation());
        }
        solutionRepository.addNewSolution();

        solution = solutionRepository.getSolutionById(0);
        station = station = extractRepository.getExtractionById(0);
        backMeasurement = extractRepository.getMeasurementById(0, 0);

        solution.setStationName(extractRepository.getMeasurementById(0, 0).getTargetName());

        solution.setDirectHorDistance(directCalculator.getHorDistance(
                backMeasurement.getTargetInclinedDistance(),
                backMeasurement.getTargetTiltAngle()
        ));

        solution.setDirectElevation(-1 * directCalculator.getDeltaZ(
                backMeasurement.getTargetInclinedDistance(),
                backMeasurement.getTargetTiltAngle()
        ) + station.getStationHeight() - backMeasurement.getTargetHeight());


    }

}

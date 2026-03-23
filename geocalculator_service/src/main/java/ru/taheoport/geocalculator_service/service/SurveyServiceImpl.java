package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.DirectCalculator;
import ru.taheoport.geocalculator_service.mapper.InverseCalculator;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SurveyServiceImpl implements SurveyService {

    private final DirectCalculator directCalculator;

    private final InverseCalculator inverseCalculator;

    private final SurveyRepository surveyRepository;
    /**
     * Converts geodetic data from different types of total stations
     *
     * @param importRequest list of strings with geodetic data
     * @return list of strings in tah-format
     */
    @Override
    public List<String> importFromTotalStation(List<String> importRequest) {
        return List.of();
    }

    /**
     * Process geodetic survey data and creates reports
     *
     * @param surveyRequest list of strings in tah-format
     * @return General report of geodetic survey processing
     */
    @Override
    public List<String> getSurveyReports(List<String> surveyRequest) {
        return List.of();
    }

    /**
     * Executes mathematical processing of geodetic survey
     */
    @Override
    public void calculateSurvey() {

    }
}

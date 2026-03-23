package ru.taheoport.geocalculator_service.service;

import java.util.List;

/**
 * This interface defines methods for processing of
 * geodetic survey data
 */
public interface SurveyService {

    /**
     * Converts geodetic data from different types of total stations
     * to list of strings in tah-format
     * @param importRequest list of strings with geodetic data
     * @return list of strings in tah-format
     */
    List<String> importFromTotalStation(List<String> importRequest);

    /**
     * Process geodetic survey data and creates reports
     * @param surveyRequest list of strings in tah-format
     * @return General report of geodetic survey processing
     */
    List<String> getSurveyReports(List<String> surveyRequest);

    /**
     * Executes mathematical processing of geodetic survey
     */
    void calculateSurvey();
}

package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;

import java.util.List;

/**
 * This class provides methods for
 * converting external data to and from the model format.
 */
@Component
public class SurveyMapperImpl implements SurveyMapper{
    /**
     * Extracts model data from the Leica total station file
     *
     * @param lines            list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromLeica(List<String> lines, SurveyRepository surveyRepository) {
        return false;
    }

    /**
     * Extracts model data from the Nikon total station file
     *
     * @param lines            list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromNikon(List<String> lines, SurveyRepository surveyRepository) {
        return false;
    }

    /**
     * Extracts model data from the Topcon total station file
     *
     * @param lines            list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromTopcon(List<String> lines, SurveyRepository surveyRepository) {
        return false;
    }

    /**
     * Creates list of strings in tah-format from survey model
     *
     * @param surveyRepository survey model
     * @return list of strings in tah-format
     */
    @Override
    public List<String> surveyToListTah(SurveyRepository surveyRepository) {
        return List.of();
    }

    /**
     * Creates general report of geodetic survey processing
     *
     * @param surveyRepository survey model
     * @return list of strings
     */
    @Override
    public List<String> surveyToReports(SurveyRepository surveyRepository) {
        return List.of();
    }
}

package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.repository.SurveyRepository;

import java.util.List;

/**
 * This interface defines methods for
 * converting external data to and from the model format.
 */
public interface SurveyMapper {

    /**
     * Extracts model data from the Leica total station file
     * @param lines list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    boolean readFromLeica(List<String> lines, SurveyRepository surveyRepository);

    /**
     * Extracts model data from the Nikon total station file
     * @param lines list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    boolean readFromNikon(List<String> lines, SurveyRepository surveyRepository);

    /**
     * Extracts model data from the Topcon total station file
     * @param lines list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    boolean readFromTopcon(List<String> lines, SurveyRepository surveyRepository);

    /**
     * Creates list of strings in tah-format from survey model
     * @param surveyRepository survey model
     * @return list of strings in tah-format
     */
    List<String> surveyToListTah(SurveyRepository surveyRepository);

    /**
     * Creates general report of geodetic survey processing
     * @param surveyRepository survey model
     * @return list of strings
     */
    List<String> surveyToResponse(SurveyRepository surveyRepository);

}

package ru.taheoport.geocalculator_service.service;

import java.util.List;

/**
 * Defines methods for extracting polygon from survey data
 */
public interface ExtractService {

    /**
     * Gets general report of polygon extraction
     * @param extractRequest List of strings
     * @return List of strings
     */
    List<String> getExtractReports(List<String> extractRequest);
}

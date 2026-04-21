package ru.taheoport.geocalculator_service.service;

import java.util.List;

/**
 * Defines methods for processing and adjusting of
 * geodetic polygon
 */
public interface PolygonService {

    /**
     * Gets general reports of processing and
     * adjusting of geodetic polygon
     * @param polygonResponse list of string with geodetic data
     * @return list of strings
     */
    List<String> getPolygonReports(List<String> polygonResponse);

    /**
     * Calculates geodetic polygon
     */
    void calculatePolygon();

    /**
     * Clears all components
     */
    void clearAll();
}

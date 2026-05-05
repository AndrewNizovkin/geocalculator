package ru.taheoport.geocalculator_service.mapper;

/**
 * This interface defines methods for
 * extraction geodetic polygon from survey data
 */
public interface ExtractCalculator {

    /**
     * Converts extractions to solutions
     */
    void ExtractionToSolution();

    /**
     * Creates new solution collection
     */
    void createSolutions();

    /**
     * Sets direct values of horizontal distance and elevation
     * for specified range of extractions
     */
    void setDirectValues();

    /**
     * Sets inverse values of horizontal distance and elevation
     * for specified range of extractions
     */
    void setInverseValues();

    /**
     * Sets average values of horizontal distance and elevation
     * for specified range of solutions
     */
    void setAverageValues();

}

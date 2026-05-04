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
     * @param start start index of extraction
     * @param end end index of extraction
     */
    void setDirectValues(int start, int end);

    /**
     * Sets inverse values of horizontal distance and elevation
     * for specified range of extractions
     * @param start start index of extraction
     * @param end end index of extraction
     */
    void setInverseValues(int start, int end);

    /**
     * Sets average values of horizontal distance and elevation
     * for specified range of solutions
     * @param start start index of extraction
     * @param end end index of extraction
     */
    void setAverageValues(int start, int end);

}

package ru.taheoport.geocalculator_service.repository;

import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;

/**
 * Defines methods for managing a collection of
 * extractions polygon data from survey
 */
public interface ExtractRepository {

    /**
     * Clears repository
     */
    void clearAll();

    /**
     * Gets number of extractions in repository
     * @return int repository size
     */
    int size();

    /**
     * Adds to repository new empty extraction
     * @return Extraction added extraction
     */
    Extraction addNewExtraction();

    /**
     * Gets element with the specified index from repository
     * @param indexExtraction int index
     * @return instance of Extraction
     */
    Extraction getExtractionById(int indexExtraction);

    /**
     * Adds new empty measurement to measurement collection of extraction
     * with specified index
     * @param indexExtraction int index in repository
     * @return Measurement empty
     */
    Measurement addNewMeasurement(int indexExtraction);

    /**
     * Gets measurement with specified index from
     * extraction with specified index
     * @param indexExtraction int extraction index
     * @param indexMeasurement int measurement index
     * @return Measurement
     */
    Measurement getMeasurementById(int indexExtraction, int indexMeasurement);
}

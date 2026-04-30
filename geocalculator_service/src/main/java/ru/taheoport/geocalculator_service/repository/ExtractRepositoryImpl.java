package ru.taheoport.geocalculator_service.repository;

import org.springframework.stereotype.Repository;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for managing a collection of
 * extractions polygon data from survey
 */
@Repository
public class ExtractRepositoryImpl implements ExtractRepository{

    private final List<Extraction> extractions;

    public ExtractRepositoryImpl() {
        this.extractions = new ArrayList<>();
    }

    /**
     * Clears repository
     */
    @Override
    public void clearAll() {
        extractions.clear();
    }

    /**
     * Gets number of extractions in repository
     * @return int repository size
     */
    @Override
    public int size() {
        return extractions.size();
    }

    /**
     * Adds to repository new empty extraction
     *
     * @return Extraction added extraction
     */
    @Override
    public Extraction addNewExtraction() {
        Extraction newExtraction = new Extraction();
        extractions.add(newExtraction);
        return newExtraction;
    }

    /**
     * Returns an element with the specified index
     * or null if the index is correct
     * @param indexExtraction int index
     * @return instance of Extraction or null
     */
    @Override
    public Extraction getExtractionById(int indexExtraction) {
        if (indexExtraction < 0 || indexExtraction >= extractions.size()) return null;
        return extractions.get(indexExtraction);
    }

    /**
     * Adds new empty measurement to measurement collection of extraction
     * with specified index
     *
     * @param indexExtraction int index in repository
     * @return Measurement empty
     */
    @Override
    public Measurement addNewMeasurement(int indexExtraction) {
        Extraction extraction = getExtractionById(indexExtraction);
        if (extraction == null) return null;

        Measurement newMeasurement = new Measurement();
        extraction.getMeasurements().add(newMeasurement);
        return newMeasurement;
    }

    /**
     * Gets measurement with specified index from
     * extraction with specified index
     *
     * @param indexExtraction  int extraction index
     * @param indexMeasurement int measurement index
     * @return Measurement
     */
    @Override
    public Measurement getMeasurementById(int indexExtraction, int indexMeasurement) {
        Extraction extraction = getExtractionById(indexExtraction);
        if (extraction == null) return null;
        if (indexMeasurement < 0 || indexMeasurement >= extraction.getMeasurements().size()) return null;

        return extraction.getMeasurements().get(indexMeasurement);
    }
}

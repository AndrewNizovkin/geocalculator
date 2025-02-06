package ru.taheoport.geocalculator_service.validator;

import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;

import java.util.List;

/**
 * This interface defines methods for checking raw data for solving Potenot Geodetic Task
 */
public interface PotenotValidator {

    /**
     * Checks the data in InverseStringRequest
     * @param potenotStringRequests list instance of PotenotStringRequest with raw geodetic data
     * @return result of check
     */
    boolean isValidPotenotStringRequest(List<PotenotStringRequest> potenotStringRequests);
}

package ru.taheoport.geocalculator_service.validator;

import ru.taheoport.geocalculator_service.dto.InverseStringRequest;

/**
 * This interface defines methods for checking raw data for solving Inverse Geodetic Task
 */
public interface InverseValidator {

    /**
     * Checks the data in InverseStringRequest
     * @param inverseStringRequest InverseStringResuest with geodetic data
     * @return result of check
     */
    boolean isValidInverseStringRequest(InverseStringRequest inverseStringRequest);
}

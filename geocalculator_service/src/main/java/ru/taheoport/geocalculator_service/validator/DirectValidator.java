package ru.taheoport.geocalculator_service.validator;

import ru.taheoport.geocalculator_service.dto.DirectStringRequest;

/**
 * This interface defines methods for checking raw data for solving Direct Geodetic Task
 */
public interface DirectValidator {

    /**
     * Checks the data in InverseStringRequest
     * @param directStringRequest instance of DirectStringRequest with raw geodetic data
     * @return result of check
     */
    boolean isValidDirectStringRequest(DirectStringRequest directStringRequest);
}

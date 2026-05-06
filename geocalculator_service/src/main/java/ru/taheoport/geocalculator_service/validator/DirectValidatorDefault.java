package ru.taheoport.geocalculator_service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.DirectStringRequest;

/**
 * This class verifies the validity of the input data for Inverse Geodetic Task.
 * @author Nizovkin A.V.
 */
@Component
@RequiredArgsConstructor
public class DirectValidatorDefault implements DirectValidator{

    private final DataValidator dataValidator;

    /**
     * Checks the data in InverseStringRequest
     * @param directStringRequest instance of DirectStringRequest with raw geodetic data
     * @return result of check
     */
    @Override
    public boolean isValidDirectStringRequest(DirectStringRequest directStringRequest) {
        return dataValidator.isValidNumber(directStringRequest.getLandmarkX()) &&
                dataValidator.isValidNumber(directStringRequest.getLandmarkY()) &&
                dataValidator.isValidHorizontalAngle(directStringRequest.getLandmarkDirection()) &&
                dataValidator.isValidNumber(directStringRequest.getBaseX()) &&
                dataValidator.isValidNumber(directStringRequest.getBaseY()) &&
                dataValidator.isValidNumber(directStringRequest.getBaseZ()) &&
                dataValidator.isValidNumber(directStringRequest.getBaseHeight()) &&
                dataValidator.isValidHorizontalAngle(directStringRequest.getTargetDirection()) &&
                dataValidator.isValidPositiveNumber(directStringRequest.getTargetInclinedDistance()) &&
                dataValidator.isValidTiltAngle(directStringRequest.getTargetTiltAngle()) &&
                dataValidator.isValidNumber(directStringRequest.getTargetHeight());
    }
}

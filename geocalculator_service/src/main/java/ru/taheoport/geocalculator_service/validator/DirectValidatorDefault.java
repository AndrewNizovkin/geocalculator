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

    private final Validator validator;

    /**
     * Checks the data in InverseStringRequest
     * @param directStringRequest instance of DirectStringRequest with raw geodetic data
     * @return result of check
     */
    @Override
    public boolean isValidDirectStringRequest(DirectStringRequest directStringRequest) {
        return validator.isValidNumber(directStringRequest.getLandmarkX()) &&
                validator.isValidNumber(directStringRequest.getLandmarkY()) &&
                validator.isValidHorizontalAngle(directStringRequest.getLandmarkDirection()) &&
                validator.isValidNumber(directStringRequest.getBaseX()) &&
                validator.isValidNumber(directStringRequest.getBaseY()) &&
                validator.isValidNumber(directStringRequest.getBaseZ()) &&
                validator.isValidNumber(directStringRequest.getBaseHeight()) &&
                validator.isValidHorizontalAngle(directStringRequest.getTargetDirection()) &&
                validator.isValidPositiveNumber(directStringRequest.getTargetInclinedDistance()) &&
                validator.isValidTiltAngle(directStringRequest.getTargetTiltAngle()) &&
                validator.isValidNumber(directStringRequest.getTargetHeight());
    }
}

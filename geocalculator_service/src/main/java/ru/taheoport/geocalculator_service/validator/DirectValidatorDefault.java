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
        return validator.isDigit(directStringRequest.getLandmarkX()) &&
                validator.isDigit(directStringRequest.getLandmarkY()) &&
                validator.isDms(directStringRequest.getLandmarkDirection()) &&
                validator.isDigit(directStringRequest.getBaseX()) &&
                validator.isDigit(directStringRequest.getBaseY()) &&
                validator.isDigit(directStringRequest.getBaseZ()) &&
                validator.isDigit(directStringRequest.getBaseHeight()) &&
                validator.isDms(directStringRequest.getTargetDirection()) &&
                validator.isDigit(directStringRequest.getTargetInclinedDistance()) &&
                validator.isDms(directStringRequest.getTargetTiltAngle()) &&
                validator.isDigit(directStringRequest.getTargetHeight());
    }
}

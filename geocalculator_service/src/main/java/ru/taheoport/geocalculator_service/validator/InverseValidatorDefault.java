package ru.taheoport.geocalculator_service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;

/**
 * This class verifies the validity of the input data for Inverse Geodetic Task.
 * @author Nizovkin A.V.
 */
@Component
@RequiredArgsConstructor
public class InverseValidatorDefault implements InverseValidator{

    private final Validator validator;

    /**
     * Checks the data in InverseStringRequest
         * @param inverseStringRequest InverseStringResuest with geodetic data
     * @return result of check
     */
    @Override
    public boolean isValidInverseStringRequest(InverseStringRequest inverseStringRequest) {
        return validator.isValidNumber(inverseStringRequest.getBaseX()) &&
                validator.isValidNumber(inverseStringRequest.getBaseY()) &&
                validator.isValidNumber(inverseStringRequest.getBaseZ()) &&
                validator.isValidNumber(inverseStringRequest.getTargetX()) &&
                validator.isValidNumber(inverseStringRequest.getTargetY()) &&
                validator.isValidNumber(inverseStringRequest.getTargetZ());
    }
}

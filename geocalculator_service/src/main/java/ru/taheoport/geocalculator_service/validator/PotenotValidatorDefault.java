package ru.taheoport.geocalculator_service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;

import java.util.List;

/**
 * This class verifies the validity of the input data for Potenot Geodetic Task.
 * @author Nizovkin A.V.
 */
@Component
@RequiredArgsConstructor
public class PotenotValidatorDefault implements PotenotValidator{

    private final DataValidator dataValidator;


    /**
     * Checks the data in InverseStringRequest
     *
     * @param potenotStringRequests list instance of PotenotStringRequest with raw geodetic data
     * @return result of check
     */
    @Override
    public boolean isValidPotenotStringRequest(List<PotenotStringRequest> potenotStringRequests) {
        if (potenotStringRequests.size() != 3) return false;
        for (PotenotStringRequest potenotStringRequest: potenotStringRequests) {
            if (!(dataValidator.isValidNumber(potenotStringRequest.getPointX()) &&
            dataValidator.isValidNumber(potenotStringRequest.getPointY()) &&
            dataValidator.isValidHorizontalAngle(potenotStringRequest.getDirection()))) {
                return false;
            }
        }
        return true;
    }
}

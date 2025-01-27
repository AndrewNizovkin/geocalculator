package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;

/**
 * This class implements Validator. Checks raw data
 */
@Component
public class ValidatorDefault implements Validator{

    /**
     * Checks the string contains a number
     * @param string String
     * @return result of check
     */
    @Override
    public boolean isDigit(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}

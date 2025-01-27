package ru.taheoport.geocalculator_service.mapper;

/**
 * This interface defines methods for data checking raw data
 */
public interface Validator {

    /**
     * Checks the string contains a number
     * @param string String
     * @return result of check
     */
    boolean isDigit(String string);

}

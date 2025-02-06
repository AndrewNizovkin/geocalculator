package ru.taheoport.geocalculator_service.validator;

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

    /**
     * Checks the string contains a angle value at d.mmss
     * @param string String
     * @return result of check
     */
    boolean isDms(String string);

}

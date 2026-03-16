package ru.taheoport.geocalculator_service.validator;

/**
 * This interface defines methods for data checking raw data
 */
public interface Validator {

    /**
     * Checks whether the argument is a positive decimal number.
     * @param value String
     * @return boolean
     */
    boolean isValidPositiveNumber(String value);

    /**
     * Checks whether the argument is a positive or negative decimal number.
     * @param value String
     * @return boolean
     */
    boolean isValidNumber(String value);

    /**
     * Checks whether the argument is a horizontal angle
     * in the range 0.0000-359.5959 in d.mmss format.
     * @param value String
     * @return boolean
     */
    boolean isValidHorizontalAngle(String value);

    /**
     * Checks whether the argument is a tilt angle
     * in the range 0 - +-89.5959 in d.mmss format.
     * @param value String
     * @return boolean
     */
    boolean isValidTiltAngle(String value);

}

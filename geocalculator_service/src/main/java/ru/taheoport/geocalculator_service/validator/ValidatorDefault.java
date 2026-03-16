package ru.taheoport.geocalculator_service.validator;

import org.springframework.stereotype.Component;

/**
 * This class implements Validator. Checks raw data
 */
@Component
public class ValidatorDefault implements Validator{

    /**
     * Checks whether the argument is a positive decimal number.
     *
     * @param value String
     * @return boolean
     */
    @Override
    public boolean isValidPositiveNumber(String value) {
        String regex = "^(\\d*\\.)?\\d+$";
        return value.matches(regex);
    }

    /**
     * Checks whether the argument is a positive or negative decimal number.
     *
     * @param value String
     * @return boolean
     */
    @Override
    public boolean isValidNumber(String value) {
        String regex = "^[-+]?(\\d*\\.)?\\d+$";
        return value.matches(regex);
    }

    /**
     * Checks whether the argument is a horizontal angle
     * in the range 0.0000-359.5959 in d.mmss format.
     *
     * @param value String
     * @return boolean
     */
    @Override
    public boolean isValidHorizontalAngle(String value) {
        String regex = "^\\d+\\.\\d\\d\\d\\d$";
        if (!value.matches(regex)) {
            return false;
        }
        String[] parts = value.split("\\.");

        int degrees = Integer.parseInt((parts[0]));
        if(degrees < 0 || degrees > 359) return false;

        int minutes = Integer.parseInt((parts[1].substring(0, 2)));
        int seconds = Integer.parseInt((parts[1].substring(2, 4)));

        return minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60;
    }

    /**
     * Checks whether the argument is a tilt angle
     * in the range 0 - +-89.5959 in d.mmss format.
     *
     * @param value String
     * @return boolean
     */
    @Override
    public boolean isValidTiltAngle(String value) {
        String regex = "^[+-]?\\d+\\.\\d\\d\\d\\d$";
        if (!value.matches(regex)) {
            return false;
        }
        String[] parts = value.split("\\.");

        int degrees = Math.abs(Integer.parseInt((parts[0])));
        if(degrees > 89) return false;

        int minutes = Integer.parseInt((parts[1].substring(0, 2)));
        int seconds = Integer.parseInt((parts[1].substring(2, 4)));

        return minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60;
    }
}

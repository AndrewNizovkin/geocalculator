package ru.taheoport.geocalculator_service.mapper;

/**
 * This interface defines methods for data analysis and transformation.
 */
public interface DataHandler {

    /**
     * Checks the string contains a number
     * @param string String
     * @return result of check
     */
    boolean isDigit(String string);

    /**
     * Converts angle value in seconds to D.MS format
     * @param seconds long
     * @return String
     */
    String secondsToDms(long seconds);

    /**
     * Converts angle value in D.MS format to seconds
     * @param angleValueAsDms String
     * @return long
     */
    long dmsToSeconds(String angleValueAsDms);

}

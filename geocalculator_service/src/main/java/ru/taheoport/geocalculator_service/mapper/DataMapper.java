package ru.taheoport.geocalculator_service.mapper;

/**
 * This interface defines methods for data analysis and transformation.
 */
public interface DataMapper {

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

    /**
     * Converts line value from millimeters to meters
     * @param millimeters long value in millimeters
     * @return String value in meters
     */
    String millimeterToMeter(long millimeters);

    /**
     * Converts line value from meters to millimeters
     * @param meters String value in meters
     * @return long value in millimeters
     */
    long meterToMillimeter(String meters);

    /**
     * Changes decimal separator from ',' to '.'
     * @param value String
     * @return String
     */
    String commaToPoint(String value);

    /**
     * Converts angle value from d.mmss format to radians
     * @param value string
     * @return double
     */
    double dmsToRadians(String value);

    /**
     * Converts angle value from radians to d.mmss format
     * @param value double
     * @return String
     */
    String radiansToDms(double value);

    /**
     * Removes insignificant zeros at the beginning of the string
     * @param value String
     * @return String
     */
    String removeFirstZero(String value);

    /**
     * Converts string value from gis16 to millimeters
     * @param value String
     * @return long line value in millimeters
     */
    long leicaToMillimeter(String value);

    /**
     * Converts string value of horizontal angle from gis16 to seconds
     * @param value String
     * @param rightShift int number of chars for right shift int rightShift
     * @return long angle value in seconds
     */
    long leicaToDirection(String value, int rightShift);

    /**
     * Converts string value of tilt angle from gis16 to seconds
     * @param value String
     * @param rightShift int number of chars for right shift int rightShift
     * @return long angle in seconds
     */
    long leicaToTiltAngle(String value, int rightShift);

    /**
     * Replaces special characters with spaces
     * @param line String
     * @return String
     */
    String specialCharsToSpaces(String line);

}

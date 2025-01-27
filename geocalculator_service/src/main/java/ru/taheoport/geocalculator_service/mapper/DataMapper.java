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

}

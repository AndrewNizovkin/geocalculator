package ru.taheoport.geocalculator_service.mapper;

public interface PotenotCalculator {

    /**
     * Calculates difference between two angles
     * @param first angle in second
     * @param second angle in second
     * @return angle in radians
     */
    double difAngle(long first, long second);
}

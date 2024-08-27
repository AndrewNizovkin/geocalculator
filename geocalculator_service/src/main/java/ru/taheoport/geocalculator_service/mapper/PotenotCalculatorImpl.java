package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;

/**
 * This class implements interface PotenotCalculator and
 * presents methods fo solving geodetic Potenot Problem
 */
@Component
public class PotenotCalculatorImpl implements PotenotCalculator{
    @Override
    public double difAngle(long first, long second) {
        double angle = (double) (second - first) / 3600;
        while (angle < 0) {
            angle += 360;
        }

        return Math.toRadians(angle);
    }
}

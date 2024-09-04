package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import static java.lang.Math.*;

/**
 * This class implements interface PotenotCalculator and
 * presents methods fo solving geodetic Potenot Problem
 */
@Component
public class PotenotCalculatorImpl implements PotenotCalculator{
    @Override
    public long difAngle(long first, long second) {
        long angle = second - first;
        while (angle < 0) {
            angle += 1296000;
        }

        return angle;
    }

    @Override
    public double targetX(double firstX, double firstY, double secondX, double secondY, double dirFromFirst, double dirFromSecond) {
        return (firstX * tan(dirFromFirst) -
                secondX * tan(dirFromSecond) +
                        secondY - firstY) /
                (tan(dirFromFirst - tan(dirFromSecond))
                        );
    }

    @Override
    public double targetY(double firstY,
                          double targetX,
                          double firstX,
                          double dirFromFirst) {
        return firstY +
                (targetX - firstX) *
                tan(dirFromFirst);
    }


}

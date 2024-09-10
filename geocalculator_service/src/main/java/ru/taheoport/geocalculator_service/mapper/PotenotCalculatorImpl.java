package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import static java.lang.Math.*;

/**
 * This class implements interface PotenotCalculator and
 * presents methods fo solving geodetic Potenot Problem
 * @author Nizovkin A.V.
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

    /**
     * Adds two angle in radians
     * @param firstAngle angle in radians
     * @param secondAngle angle in radians
     * @return angle in a range 0 - 2*Pi radians
     */
    @Override
    public double addAngle(double firstAngle, double secondAngle) {
        double result = firstAngle + secondAngle;
        while (result >= 2 * PI) {
            result -= 2 * PI;
        }
        return result;
    }

    @Override
    public double targetX(
            double firstX,
            double firstY,
            double secondX,
            double secondY,
            double dirFromFirst,
            double dirFromSecond) {

        double numerator = firstX * tan(dirFromFirst) - secondX * tan(dirFromSecond) + secondY - firstY;

        double denominator = tan(dirFromFirst) - tan(dirFromSecond);

        return numerator / denominator;
    }

    @Override
    public double targetY(
            double firstY,
            double targetX,
            double firstX,
            double dirFromFirst) {
        return firstY +
                (targetX - firstX) *
                tan(dirFromFirst);
    }

    @Override
    public double dirFromFirst(
            double firstX,
            double firstY,
            double secondX,
            double secondY,
            double thirdX,
            double thirdY,
            double firstAngle,
            double secondAngle) {

        double dir = 0.0;
        double dY = (secondY - firstY) * 1 / tan(firstAngle) + (firstY - thirdY) * 1 /tan(secondAngle) - secondX + thirdX;
        double dX = ((secondX - firstX) * 1 / tan(firstAngle) + (firstX - thirdX) * 1 / tan(secondAngle) + secondY - thirdY);

        if (dX == 0) {
            if (dY > 0) {
                return PI / 2;
            }
            if (dY < 0) {
                return PI + PI / 2;
            }

        } else {
            dir = atan(dY / dX);
            if (dY == 0) {
                if (dX < 0) {
                    return PI;
                } else {
                    return 0;
                }
            }
            if ( dY < 0) {
                if (dX < 0) {
                    dir += PI;
                } else {
                    dir += 2 * PI;
                }

            } else {
                if (dX < 0) {
                    dir += PI;
                }
            }
        }

        return dir;

    }

}

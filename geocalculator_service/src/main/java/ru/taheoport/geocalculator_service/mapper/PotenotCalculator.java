package ru.taheoport.geocalculator_service.mapper;

public interface PotenotCalculator {

    /**
     * Calculates difference between two angles
     * @param first angle in second
     * @param second angle in second
     * @return angle in radians
     */
    long difAngle(long first, long second);

    /**
     * Adds two angle in radians
     * @param firstAngle angle in radians
     * @param secondAngle angle in radians
     * @return angle in a range 0 - 2*Pi radians
     */
    double addAngle(double firstAngle, double secondAngle);

    /**
     * Calculates X coordinate with Delambra formula
     * @param firstX
     * @param firstY
     * @param secondX
     * @param secondY
     * @param dirFromFirst
     * @param dirFromSecond
     * @return
     */
    double targetX(
            double firstX,
            double firstY,
            double secondX,
            double secondY,
            double dirFromFirst,
            double dirFromSecond
    );

    /**
     * Calculates Y coordinate with Delambra formula
     * @param firstY
     * @param targetX
     * @param dirFromFirst
     * @return
     */
    double targetY(
      double firstY,
      double targetX,
      double firstX,
      double dirFromFirst
    );

    /**
     * Calculates directional angle to target from first point
     * @param firstX
     * @param firstY
     * @param secondX
     * @param secondY
     * @param thirdX
     * @param thirdY
     * @param firstAngle
     * @param secondAngle
     * @return Direction angle in radians
     */
    double dirFromFirst(
            double firstX,
            double firstY,
            double secondX,
            double secondY,
            double thirdX,
            double thirdY,
            double firstAngle,
            double secondAngle
    );
}

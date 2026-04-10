package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.model.BindType;

/**
 * This interface defines methods for
 * adjusting geodetic polygon
 */
public interface PolygonCalculator {

    /**
     * Defines type of polygon binding to the reference geodetic network
     */
    void setBindType();

    /**
     * Defines the sum of the horizontal distance of the polygon
     * in the specified range of station indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setPerimeter(int start, int end);

    /**
     * Defines the correction to the horizontal angles of the polygon
     * in the specified range of station indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setCorrectionHorAngle(int start, int end);

    /**
     * Sets the direction angle for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setDirectionAngle(int start, int end);

    /**
     * Sets the deltaX and deltaY for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setDeltaXY(int start, int end);

    /**
     * Sets the correctionX and correctionY for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setCorrectionXY(int start, int end);

    /**
     * Sets the correctionZ for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setCorrectionZ(int start, int end);

    /**
     * Sets the final XYZ coordinates for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end int end of range
     */
    void setXYZ(int start, int end);
}

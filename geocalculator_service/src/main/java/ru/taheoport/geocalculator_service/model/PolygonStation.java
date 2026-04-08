package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolygonStation {

    /**
     * Polygon station name
     */
    String stationName = "noname";

    /**
     * The horizontal angle between the direction to the back point
     * and the direction to the front point in second
     */
    long horAngle = 0;

    /**
     * The horizontal projection of distance from this station
     * to front point of polygon in millimeters
     */
    long horDistance = 0;

    /**
     * The elevation between this point and
     * front point of polygon in millimeters
     */
    long elevation = 0;

    /**
     * Coordinate X of this polygon station in millimeters
     */
    long stationX = 0;

    /**
     * Coordinate Y of this polygon station in millimeters
     */
    long stationY = 0;

    /**
     * Coordinate Z of this polygon station in millimeters
     */
    long stationZ = 0;

    /**
     * True if this station is a base point with known coordinates
     */
    boolean status = false;

    /**
     * Correction of the horizontal angle in seconds
     */
    double correctionHorAngle = 0.0;

    /**
     * Direction angle from this station
     * to front point of polygon in seconds
     */
    long directionAngle = 0;

    /**
     * Increment of coordinates along the X axis in millimeters
     */
    long deltaX = 0;

    /**
     * Increment of coordinates along the Y axis in millimeters
     */
    long deltaY = 0;

    /**
     * Increment of coordinates along the Z axis in millimeters
     */
    long deltaZ = 0;

    /**
     * X-axis correction in millimeters
     */
    long correctionX = 0;

    /**
     * Y-axis correction in millimeters
     */
    long correctionY = 0;

    /**
     * Z-axis correction in millimeters
     */
    double correctionZ = 0.0;
}

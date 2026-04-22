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
    private String stationName = "noname";

    /**
     * The horizontal angle between the direction to the back point
     * and the direction to the front point in second
     */
    private long horAngle = 0;

    /**
     * The horizontal projection of distance from this station
     * to front point of polygon in millimeters
     */
    private long horDistance = 0;

    /**
     * The elevation between this point and
     * front point of polygon in millimeters
     */
    private long elevation = 0;

    /**
     * Coordinate X of this polygon station in millimeters
     */
    private long stationX = 0;

    /**
     * Coordinate Y of this polygon station in millimeters
     */
    private long stationY = 0;

    /**
     * Coordinate Z of this polygon station in millimeters
     */
    private long stationZ = 0;

    /**
     * True if this station is a base point with known coordinates
     */
    private boolean status = false;

    /**
     * Correction of the horizontal angle in seconds
     */
    private double correctionHorAngle = 0.0;

    /**
     * Direction angle from this station
     * to front point of polygon in seconds
     */
    private long directionAngle = 0;

    /**
     * Increment of coordinates along the X axis in millimeters
     */
    private long deltaX = 0;

    /**
     * Increment of coordinates along the Y axis in millimeters
     */
    private long deltaY = 0;

    /**
     * Increment of coordinates along the Z axis in millimeters
     */
    private long elevationCorrected = 0;

    /**
     * X-axis correction in millimeters
     */
    private long correctionX = 0;

    /**
     * Y-axis correction in millimeters
     */
    private long correctionY = 0;

    /**
     * Z-axis correction in millimeters
     */
    private double correctionZ = 0.0;
}

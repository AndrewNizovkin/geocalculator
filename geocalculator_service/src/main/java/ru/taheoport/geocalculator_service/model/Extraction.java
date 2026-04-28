package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class encapsulates the data extracted
 * from the geodetic survey to build the polygon.
 */
@Getter
@Setter
@NoArgsConstructor
public class Extraction {

    /**
     * Name of polygon station
     */
    private String stationName = "noname";

    /**
     * Height of total station over point
     */
    private long stationHeight = 0;

    /**
     * Name of back point
     */
    private String backName = "noname";

    /**
     * Direction to back point in seconds
     */
    private long backDirection = 0;

    /**
     * Inclined distance from station to back point in millimeters
     */
    private long backInclinedDistance = 0;

    /**
     * Tilt angle line from station to back point in seconds
     */
    private long backTiltAngle = 0;

    /**
     * Height of the reflector over back point in millimeters
     */
    private long backHeight = 0;

    /**
     * Name of front point
     */
    private String frontName = "noname";

    /**
     * Direction to front point in seconds
     */
    private long frontDirection = 0;

    /**
     * Inclined distance from station to front point in millimeters
     */
    private long frontInclinedDistance = 0;

    /**
     * Tilt angle line from station to front point in seconds
     */
    private long frontTiltAngle = 0;

    /**
     * Height of the reflector over front point in millimeters
     */
    private long frontHeight = 0;

    /**
     * The horizontal angle between
     * the directions to the back and front points in seconds
     */
    private long horAngle = 0;

    /**
     * The distance between the station and the front point
     * is in direct order in millimeters
     */
    private long directHorDistance = 0;

    /**
     * The elevation between the station and the front point
     * is in direct order in millimeters
     */
    private long directElevation = 0;

    /**
     * The distance between the station and the front point
     * is in inverse order in millimeters
     */
    private long inverseHorDistance = 0;

    /**
     * The elevation between the station and the front point
     * is in inverse order in millimeters
     */
    private long inverseElevation = 0;

    /**
     * The average distance between the station and the front point in millimeters
     */
    private long averageHorDistance = 0;

    /**
     * The average elevation between the station and the front point in millimeters
     */
    private long averageElevation = 0;

    /**
     * The difference between direct and inverse horizontal distance in millimeters
     */
    private long deltaHorDistance = 0;

    /**
     * The difference between direct and inverse elevation in millimeters
     */
    private long deltaElevation = 0;


}

package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class encapsulates extraction solutions.
 */
@Getter
@Setter
@NoArgsConstructor
public class Solution {

    /**
     * Name of polygon station
     */
    private String stationName = "noname";

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

package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the parameters of a geodetic survey station
 * and a collection of measurements.
 */
@Getter
@Setter
@NoArgsConstructor
public class SurveyStation {

    /**
     * Name of the survey station
     */
    private String stationName;

    /**
     * Coordinate X of the survey station in millimeters
     */
    private long stationX;

    /**
     * Coordinate Y of the survey station in millimeters
     */
    private long stationY;

    /**
     * Coordinate Z of the survey station in millimeters
     */
    private long stationZ;

    /**
     * The height of the tool over the point in millimeters
     */
    private long stationHeight;

    /**
     * Direction to back point (landmark) in seconds
     */
    private long orDirection;

    /**
     * Name of the back point (landmark)
     */
    private String orName;

    /**
     * Coordinate X of the back point (landmark) in millimeters
     */
    private long orX;

    /**
     * Coordinate Y of the back point (landmark) in millimeters
     */
    private long orY;

    /**
     * Direction angle of baseline in seconds
     */
    private long baseDirectionAngle;

    /**
     * Collection of measurements of this survey station
     */
    private List<Measurement> measurements = new ArrayList<>();


}

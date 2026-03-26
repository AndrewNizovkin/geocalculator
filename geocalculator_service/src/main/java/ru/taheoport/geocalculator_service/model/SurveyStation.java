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
    private String stationName = "noname";

    /**
     * Coordinate X of the survey station in millimeters
     */
    private long stationX = 0;

    /**
     * Coordinate Y of the survey station in millimeters
     */
    private long stationY = 0;

    /**
     * Coordinate Z of the survey station in millimeters
     */
    private long stationZ = 0;

    /**
     * The height of the tool over the point in millimeters
     */
    private long stationHeight = 0;

    /**
     * Direction to back point (landmark) in seconds
     */
    private long orDirection = 0;

    /**
     * Name of the back point (landmark)
     */
    private String orName = "noname";

    /**
     * Coordinate X of the back point (landmark) in millimeters
     */
    private long orX = 0;

    /**
     * Coordinate Y of the back point (landmark) in millimeters
     */
    private long orY = 0;

    /**
     * Direction angle of baseline in seconds
     */
    private long baseDirectionAngle = 0;

    /**
     * Collection of measurements of this survey station
     */
    private List<Measurement> measurements = new ArrayList<>();


}

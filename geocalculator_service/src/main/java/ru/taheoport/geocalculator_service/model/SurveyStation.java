package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
     * Coordinate X of the survey station in meters
     */
    private  double stationX;

    /**
     * Coordinate Y of the survey station in meters
     */
    private double stationY;

    /**
     * Coordinate Z of the survey station in meters
     */
    private double stationZ;

    /**
     * The height of the tool over the point in meters
     */
    private double stationHeight;

    /**
     * Direction to back point (landmark) in radians
     */
    private double orDirection;

    /**
     * Name of the back point (landmark)
     */
    private String orName;

    /**
     * Coordinate X of the back point (landmark) in meters
     */
    private double orX;

    /**
     * Coordinate Y of the back point (landmark) in meters
     */
    private double orY;

    /**
     * Direction angle of baseline
     */
    private double baseDirectionAngle;

    /**
     * Collection of measurements of this survey station
     */
    private List<Measurement> measurements;


}

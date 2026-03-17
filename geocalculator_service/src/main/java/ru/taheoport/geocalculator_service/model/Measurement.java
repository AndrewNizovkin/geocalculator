package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class encapsulates geodetic measurements
 * and the defined characteristics of the target point.
 * @author Nizovkin_A.V.
 */
@Getter
@Setter
@NoArgsConstructor
public class Measurement {

    /**
     * Name of the target (reference point, picket)
     */
    private String targetName;

    /**
     * Direction from station to this target in radians
     */
    private double targetDirection;

    /**
     * Inclined distance from station to this target in meters
     */
    private double targetInclinedDistance;

    /**
     * Tilt angle to target in radians
     */
    private double targetTiltAngle;

    /**
     * The height of the reflector above the target in meters
     */
    private double targetHeight;

    /**
     * The horizontal distance between the station and the target
     */
    private double targetHorizontalDistance;

    /**
     * Direction angle from station to this target in radians
     */
    private double targetDirectionAngle;

    /**
     * The X-axis coordinate increment between the station and this target in meters
     */
    private double targetDeltaX;

    /**
     * The Y-axis coordinate increment between the station and this target in meters
     */
    private double targetDeltaY;

    /**
     * The Z-axis coordinate increment between the station and this target in meters
     */
    private double targetDeltaZ;

    /**
     * Coordinate X of the target in meters
     */
    private double targetX;

    /**
     * Coordinate Y of the target in meters
     */
    private double targetY;

    /**
     * Coordinate Z of the target in meters
     */
    private double targetZ;


}

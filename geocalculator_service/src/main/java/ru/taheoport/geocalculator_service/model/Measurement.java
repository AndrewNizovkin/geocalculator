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
     * Direction from station to this target in seconds
     */
    private long targetDirection;

    /**
     * Inclined distance from station to this target in millimeters
     */
    private long targetInclinedDistance;

    /**
     * Tilt angle to target in seconds
     */
    private long targetTiltAngle;

    /**
     * The height of the reflector over the target in millimeters
     */
    private long targetHeight;

    /**
     * The horizontal distance between the station and the target
     */
    private long targetHorizontalDistance;

    /**
     * Direction angle from station to this target in seconds
     */
    private long targetDirectionAngle;

    /**
     * The X-axis coordinate increment between the station and this target in millimeters
     */
    private long targetDeltaX;

    /**
     * The Y-axis coordinate increment between the station and this target in millimeters
     */
    private long targetDeltaY;

    /**
     * The Z-axis coordinate increment between the station and this target in millimeters
     */
    private long targetDeltaZ;

    /**
     * Coordinate X of the target in millimeters
     */
    private long targetX;

    /**
     * Coordinate Y of the target in millimeters
     */
    private long targetY;

    /**
     * Coordinate Z of the target in millimeters
     */
    private long targetZ;


}

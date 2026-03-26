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
    private String targetName = "noname";

    /**
     * Direction from station to this target in seconds
     */
    private long targetDirection = 0;

    /**
     * Inclined distance from station to this target in millimeters
     */
    private long targetInclinedDistance = 0;

    /**
     * Tilt angle to target in seconds
     */
    private long targetTiltAngle = 0;

    /**
     * The height of the reflector over the target in millimeters
     */
    private long targetHeight = 0;

    /**
     * The horizontal distance between the station and the target
     */
    private long targetHorizontalDistance = 0;

    /**
     * Direction angle from station to this target in seconds
     */
    private long targetDirectionAngle = 0;

    /**
     * The X-axis coordinate increment between the station and this target in millimeters
     */
    private long targetDeltaX = 0;

    /**
     * The Y-axis coordinate increment between the station and this target in millimeters
     */
    private long targetDeltaY = 0;

    /**
     * The Z-axis coordinate increment between the station and this target in millimeters
     */
    private long targetDeltaZ = 0;

    /**
     * Coordinate X of the target in millimeters
     */
    private long targetX = 0;

    /**
     * Coordinate Y of the target in millimeters
     */
    private long targetY = 0;

    /**
     * Coordinate Z of the target in millimeters
     */
    private long targetZ = 0;

}

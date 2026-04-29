package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class encapsulates geodetic measurements
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

}

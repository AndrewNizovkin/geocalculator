package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class encapsulates the data of a direct geodetic problem being solved
 */
@Getter
@Setter
@NoArgsConstructor
public class DirectStringRequest {

    /**
     * Coordinate X of landmark in meters
     */
    private String landmarkX;

    /**
     * Coordinate Y of landmark in meters
     */
    private String landmarkY;

    /**
     * Direction to landmark in d.mmss format
     */
    private String landmarkDirection;

    /**
     * Coordinate X of base in meters
     */
    private String baseX;

    /**
     * Coordinate Y of base in meters
     */
    private String baseY;

    /**
     * Coordinate Z of base in meters
     */

    private String baseZ;

    /**
     * Tool height base point in meters
     */
    private String baseHeight;

    /**
     * Direction to target in d.mmss format
     */
    private String targetDirection;

    /**
     * Length of the line between the base and the target in meters
     */
    private String targetInclinedDistance;

    /**
     * Tilt angle of line from base to target in d.mmss format
     */
    private String targetTiltAngle;

    /**
     * target height of target in meters
     */
    private String targetHeight;
}

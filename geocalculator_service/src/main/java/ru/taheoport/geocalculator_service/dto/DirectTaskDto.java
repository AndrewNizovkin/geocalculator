package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates the data of a direct geodetic problem being solved
 */
@Data
@NoArgsConstructor
public class DirectTaskDto {

    /**
     * Coordinate X of landmark in millimeters
     */
    private long landmarkX;

    /**
     * Coordinate Y of landmark in millimeters
     */
    private long landmarkY;

    /**
     * Direction to landmark in seconds
     */
    private long landmarkDirection;

    /**
     * Coordinate X of base in millimeters
     */
    private long baseX;

    /**
     * Coordinate Y of base in millimeters
     */
    private long baseY;

    /**
     * Coordinate Z of base in millimeters
     */
    private long baseZ;

    /**
     * Direction to target in seconds
     */
    private long targetDirection;

    /**
     * Length of the line between the base and the target
     */
    private long targetInclinedDistance;

    /**
     * Tilt angle of line from base to target
     */
    private long targetTiltAngle;

    /**
     * Coordinate X of target in millimeters
     */
    private long targetX;

    /**
     * Coordinate Y of target in millimeters
     */
    private long targetY;

    /**
     * Coordinate Z of target in millimeters
     */
    private long targetZ;
}

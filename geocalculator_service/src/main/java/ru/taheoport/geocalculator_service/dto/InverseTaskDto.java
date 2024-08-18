package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InverseTaskDto {

    /**
     * Coordinate X base point, in millimeters
     */
    private long baseX;

    /**
     * Coordinate Y base point, in millimeters
     */
    private long baseY;

    /**
     * Coordinate Z base point, in millimeters
     */
    private long baseZ;

    /**
     * Coordinate X target point, in millimeters
     */
    private long targetX;

    /**
     * Coordinate Y target point, in millimeters
     */
    private long targetY;

    /**
     * Coordinate Z target point, in millimeters
     */
    private long targetZ;

    /**
     * The angle between the direction to the north and
     * the direction to the target, in seconds
     */
    private long direction;

    /**
     * Horizontal distance between base and target, in millimeters
     */
    private long horDistance;

    /**
     * Inclined distance between base and target, in millimeters
     */
    private long inclinedDistance;

    /**
     * The tilt angle of base->target line, in seconds
     */
    private long tiltAngle;

    /**
     * The height difference between the target and the base
     */
    private long elevation;
}

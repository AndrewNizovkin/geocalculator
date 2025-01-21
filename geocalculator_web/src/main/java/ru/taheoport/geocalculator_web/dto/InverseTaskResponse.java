package ru.taheoport.geocalculator_web.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InverseTaskResponse extends InverseTaskRequest{

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

package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverseStringResponse extends InverseStringRequest{
    /**
     * The angle between the direction to the north and
     * the direction to the target, in seconds
     */
    private String direction;

    /**
     * Horizontal distance between base and target, in millimeters
     */
    private String horDistance;

    /**
     * Inclined distance between base and target, in millimeters
     */
    private String inclinedDistance;

    /**
     * The tilt angle of base->target line, in seconds
     */
    private String tiltAngle;

    /**
     * The height difference between the target and the base
     */
    private String elevation;
}

package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverseStringResponse{
    /**
     * The angle between the direction to the north and
     * the direction to the target, in d.mmss format
     */
    private String direction;

    /**
     * Horizontal distance between base and target, in meters
     */
    private String horDistance;

    /**
     * Inclined distance between base and target, in meters
     */
    private String inclinedDistance;

    /**
     * The tilt angle of base->target line, in d.mmss format
     */
    private String tiltAngle;

    /**
     * The height difference between the target and the base in meters
     */
    private String elevation;
}

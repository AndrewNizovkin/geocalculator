package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverseStringResponse{

    /**
     * Response header
     */
    private String header = "OK";

    /**
     * The angle between the direction to the north and
     * the direction to the target, in d.mmss format
     */
    private String direction = "0.0000";

    /**
     * Horizontal distance between base and target, in meters
     */
    private String horDistance = "0.000";

    /**
     * Inclined distance between base and target, in meters
     */
    private String inclinedDistance = "0.000";

    /**
     * The tilt angle of base->target line, in d.mmss format
     */
    private String tiltAngle = "0.0000";

    /**
     * The height difference between the target and the base in meters
     */
    private String elevation = "0.000";
}

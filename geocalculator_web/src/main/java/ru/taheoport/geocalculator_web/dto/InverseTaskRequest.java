package ru.taheoport.geocalculator_web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverseTaskRequest {

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
}

package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverseStringRequest {
    /**
     * Coordinate X base point, in millimeters
     */
    private String baseX;

    /**
     * Coordinate Y base point, in millimeters
     */
    private String baseY;

    /**
     * Coordinate Z base point, in millimeters
     */
    private String baseZ;

    /**
     * Coordinate X target point, in millimeters
     */
    private String targetX;

    /**
     * Coordinate Y target point, in millimeters
     */
    private String targetY;

    /**
     * Coordinate Z target point, in millimeters
     */
    private String targetZ;
}

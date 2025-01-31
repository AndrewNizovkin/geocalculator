package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InverseStringRequest {
    /**
     * Coordinate X base point, in meters
     */
    private String baseX;

    /**
     * Coordinate Y base point, in meters
     */
    private String baseY;

    /**
     * Coordinate Z base point, in meters
     */
    private String baseZ;

    /**
     * Coordinate X target point, in meters
     */
    private String targetX;

    /**
     * Coordinate Y target point, in meters
     */
    private String targetY;

    /**
     * Coordinate Z target point, in meters
     */
    private String targetZ;
}

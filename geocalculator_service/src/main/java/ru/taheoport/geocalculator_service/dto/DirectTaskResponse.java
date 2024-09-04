package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates the result of direct geodetic problem
 */
@Data
@NoArgsConstructor
public class DirectTaskResponse {

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

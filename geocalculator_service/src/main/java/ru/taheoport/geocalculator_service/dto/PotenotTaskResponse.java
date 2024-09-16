package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates result of Potenot problem calculation
 */
@Data
@NoArgsConstructor
public class PotenotTaskResponse {
    /**
     * Coordinate X in millimeters
     */
    private long pointX;

    /**
     * Coordinate Y in millimeters
     */
    private long pointY;

    /**
     * Constructor with all arguments
     * @param pointX X coordinate
     * @param pointY X coordinate
     */
    public PotenotTaskResponse(long pointX, long pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }
}

package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates point coordinates and direction to this point from target
 */
@Data
@NoArgsConstructor
public class PotenotTaskRequest {

    /**
     * Coordinate X in millimeters
     */
    private long pointX;

    /**
     * Coordinate Y in millimeters
     */
    private long pointY;

    /**
     * Direction in seconds
     */
    private long direction;

    /**
     * Constructor with all arguments
     * @param pointX X coordinate in millimeters
     * @param pointY Y coordinate in millimeters
     * @param direction Direction to this point in seconds
     */
    public PotenotTaskRequest(long pointX, long pointY, long direction) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.direction = direction;
    }
}

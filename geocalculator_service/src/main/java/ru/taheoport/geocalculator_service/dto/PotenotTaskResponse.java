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
     * X accuracy in millimeters
     */
    private long deltaX;

    /**
     * Y accuracy in millimeters
     */
    private long deltaY;

}

package ru.taheoport.geocalculator_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates result of Potenot problem calculation
 */
@Data
@NoArgsConstructor
@Schema(name = "Результат решения прямой геодезической засечки")
public class PotenotTaskResponse {
    /**
     * Coordinate X in millimeters
     */
    @Schema(name = "Координата X, мм.")
    private long pointX;

    /**
     * Coordinate Y in millimeters
     */
    @Schema(name = "Координата Y, мм.")
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

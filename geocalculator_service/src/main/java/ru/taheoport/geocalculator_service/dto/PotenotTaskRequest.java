package ru.taheoport.geocalculator_service.dto;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates point coordinates and direction to this point from target
 */
@Data
@NoArgsConstructor
//@Schema(name = "Исходные данные для решения обратной засечки.")
public class PotenotTaskRequest {

    /**
     * Coordinate X in millimeters
     */
//    @Schema(name = "Координата X, мм.")
    private long pointX;

    /**
     * Coordinate Y in millimeters
     */
//    @Schema(name = "Координата Y, мм.")
    private long pointY;

    /**
     * Direction in seconds
     */
//    @Schema(name = "Угловое направление на эту точку с определяемой, сек.")
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

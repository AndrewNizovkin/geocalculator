package ru.taheoport.geocalculator_service.dto;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates the result of direct geodetic problem
 */
@Data
@NoArgsConstructor
//@Schema(name = "Результат решения прямой геодезической задачи. Координаты определяемой точки")
public class DirectTaskResponse {

    /**
     * Coordinate X of target in millimeters
     */
//    @Schema(name = "Координата X, мм.")
    private long targetX;

    /**
     * Coordinate Y of target in millimeters
     */
//    @Schema(name = "Координата Y, мм.")
    private long targetY;

    /**
     * Coordinate Z of target in millimeters
     */
//    @Schema(name = "Координата Z, мм.")
    private long targetZ;
}

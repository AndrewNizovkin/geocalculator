package ru.taheoport.geocalculator_service.dto;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates result of solving inverse geodetic task
 * @author Nizovkin A.V.
 */
@Data
@NoArgsConstructor
//@Schema(name = "Результат решения обратной геодезической задачи")
public class InverseTaskResponse {

    /**
     * The angle between the direction to the north and
     * the direction to the target, in seconds
     */
//    @Schema(name = "Дирекционный угол линии, сек.")
    private long direction;

    /**
     * Horizontal distance between base and target, in millimeters
     */
//    @Schema(name = "Горизонтальное проложение, мм.")
    private long horDistance;

    /**
     * Inclined distance between base and target, in millimeters
     */
//    @Schema(name = "Наклонное расстояние, мм.")
    private long inclinedDistance;

    /**
     * The tilt angle of base->target line, in seconds
     */
//    @Schema(name = "Угол наклона линии, сек.")
    private long tiltAngle;

    /**
     * The height difference between the target and the base
     */
//    @Schema(name = "Превышение, мм.")
    private long elevation;
}

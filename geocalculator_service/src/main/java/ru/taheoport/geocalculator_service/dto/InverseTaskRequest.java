package ru.taheoport.geocalculator_service.dto;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@Schema(name = "Исходные данные для решения обратной геодезической задачи")
public class InverseTaskRequest {

    /**
     * Coordinate X base point, in millimeters
     */
//    @Schema(name = "Координата X точки base, мм.")
    private long baseX;

    /**
     * Coordinate Y base point, in millimeters
     */
//    @Schema(name = "Координата Y точки base, мм.")
    private long baseY;

    /**
     * Coordinate Z base point, in millimeters
     */
//    @Schema(name = "Координата Z точки base, мм.")
    private long baseZ;

    /**
     * Coordinate X target point, in millimeters
     */
//    @Schema(name = "Координата X точки target, мм.")
    private long targetX;

    /**
     * Coordinate Y target point, in millimeters
     */
//    @Schema(name = "Координата Y точки target, мм.")
    private long targetY;

    /**
     * Coordinate Z target point, in millimeters
     */
//    @Schema(name = "Координата Z точки target, мм.")
    private long targetZ;
}

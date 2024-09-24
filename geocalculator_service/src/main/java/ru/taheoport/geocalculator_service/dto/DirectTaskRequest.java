package ru.taheoport.geocalculator_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates the data of a direct geodetic problem being solved
 */
@Data
@NoArgsConstructor
@Schema(name = "Исходные данные для решения прямой геодезической задачи")
public class DirectTaskRequest {

    /**
     * Coordinate X of landmark in millimeters
     */
    @Schema(name = "Координата X ориентира (landmark), мм.")
    private long landmarkX;

    /**
     * Coordinate Y of landmark in millimeters
     */
    @Schema(name = "Координата Y ориентира (landmark), мм.")
    private long landmarkY;

    /**
     * Direction to landmark in seconds
     */
    @Schema(name = "Угловое направление base->landmark, сек.")
    private long landmarkDirection;

    /**
     * Coordinate X of base in millimeters
     */
    @Schema(name = "Координата X измерительной станции (base), мм.")
    private long baseX;

    /**
     * Coordinate Y of base in millimeters
     */
    @Schema(name = "Координата Y измерительной станции (base), мм.")
    private long baseY;

    /**
     * Coordinate Z of base in millimeters
     */
    @Schema(name = "Координата Z измерительной станции (base), мм.")
    private long baseZ;

    /**
     * Direction to target in seconds
     */
    @Schema(name = "Угловое направление base->target, сек.")
    private long targetDirection;

    /**
     * Length of the line between the base and the target
     */
    @Schema(name = "Наклонная длина линии base->target, мм.")
    private long targetInclinedDistance;

    /**
     * Tilt angle of line from base to target
     */
    @Schema(name = "Угол наклона линии base->target, сек.")
    private long targetTiltAngle;
}

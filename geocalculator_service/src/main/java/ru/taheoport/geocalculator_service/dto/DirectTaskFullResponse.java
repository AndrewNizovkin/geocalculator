package ru.taheoport.geocalculator_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectTaskFullResponse extends DirectTaskRequest{
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

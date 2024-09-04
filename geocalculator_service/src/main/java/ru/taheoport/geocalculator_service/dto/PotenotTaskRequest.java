package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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

//    /**
//     * X accuracy in millimeters
//     */
//    private long deltaX;
//
//    /**
//     * Y accuracy in millimeters
//     */
//    private long deltaY;

}

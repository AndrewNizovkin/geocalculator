package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectStringResponse{

    /**
     * Response header
     */
    private String header = "OK";

    /**
     * Coordinate X of target in meters
     */
    private String targetX = "0.000";

    /**
     * Coordinate Y of target in meters
     */
    private String targetY = "0.000";

    /**
     * Coordinate Z of target in meters
     */
    private String targetZ = "0.000";

}

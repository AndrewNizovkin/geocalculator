package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PotenotStringResponse {

    /**
     * Response header
     */
    private String header = "OK";

    /**
     * Coordinate X in meters
     */
    private String pointX = "0.000";

    /**
     * Coordinate Y in meters
     */
    private String pointY = "0.000";

}

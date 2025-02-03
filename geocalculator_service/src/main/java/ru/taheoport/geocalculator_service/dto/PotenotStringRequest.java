package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PotenotStringRequest {
    /**
     * Coordinate X in meters
     */
    private String pointX;

    /**
     * Coordinate Y in meters
     */
    private String pointY;

    /**
     * Direction in d.mmss format
     */
    private String direction;

}

package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectStringResponse extends DirectStringRequest{

    /**
     * Coordinate X of target in meters
     */
    private String targetX;

    /**
     * Coordinate Y of target in meters
     */
    private String targetY;

    /**
     * Coordinate Z of target in meters
     */
    private String targetZ;

}

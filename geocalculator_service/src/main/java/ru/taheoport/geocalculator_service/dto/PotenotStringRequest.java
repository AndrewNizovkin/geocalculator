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

    /**
     * Constructor with all arguments
     * @param pointX X coordinate in meters
     * @param pointY Y coordinate in meters
     * @param direction Direction to this point in d.mmss format
     */
    public PotenotStringRequest(String pointX, String pointY, String direction) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.direction = direction;
    }

}

package ru.taheoport.geocalculator_service.dto;

public class PotenotTaskResponse {
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

    /**
     * X accuracy in millimeters
     */
    private long deltaX;

    /**
     * Y accuracy in millimeters
     */
    private long deltaY;

}

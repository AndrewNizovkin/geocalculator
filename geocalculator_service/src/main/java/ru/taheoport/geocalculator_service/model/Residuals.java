package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * This class encapsulates actual processing residuals
 */
@Getter
@Setter
@NoArgsConstructor
@Component
public class Residuals {

    /**
     * Actual elevation residuals in millimeters
     */
    private long elevation;

    /**
     * Actual angle residuals in seconds
     */
    private long angle;

    /**
     * X-axis linear residuals in millimeters
     */
    private long linearX;

    /**
     * Y-axis linear residuals in millimeters
     */
    private long linearY;

    /**
     * Actual absolute linear mistake
     */
    private long absolute;

    /**
     * Actual relative linear mistake
     */
    private String relative;

    /**
     * Polygon perimeter
     */
    private long perimeter;

    /**
     * The type of polygon binding to the reference geodetic network
     */
    private BindType bindType = BindType.ZZ;

}

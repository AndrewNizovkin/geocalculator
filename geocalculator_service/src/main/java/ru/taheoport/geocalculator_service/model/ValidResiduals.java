package ru.taheoport.geocalculator_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * This class encapsulates valid residuals
 * for geodetic polygon
 */
@Getter
@Setter
@NoArgsConstructor
@Component
public class ValidResiduals {

    /**
     * Coefficient for determining the elevation valid residuals
     */
    private int validElevation;

    /**
     * Coefficient for determining the angle valid residuals
     */
    private int validAngle;

    /**
     * Value of absolute valid linear residuals
     */
    private String validAbsolute;

    /**
     * The value of the permissible relative accuracy of linear measurements
     */
    private String validRelative;
}

package ru.taheoport.geocalculator_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * This class encapsulates actual and valid residuals
 * for polygon response
 */
@Component
@Getter
@Setter
public class ReportResiduals {

    String perimeter;

    String actualElevation;

    String actualAngle;

    String actualX;

    String actualY;

    String actualAbsolute;

    String actualRelative;

    String validElevation;

    String validAngle;

    String validAbsolute;

    String validRelative;
}

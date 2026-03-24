package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import static java.lang.Math.*;

@Component
public class DirectCalculatorDefault implements DirectCalculator{

    /**
     * Defines the directional angle of the direction to the target
     * @param landmarkDirectionalAngle directional angle of the direction to the landmark in seconds
     * @param landmarkDirection direction to landmark in seconds
     * @param targetDirection direction to target in seconds
     * @return result in seconds
     */
    @Override
    public long getDirectionalAngle(long landmarkDirectionalAngle, long landmarkDirection, long targetDirection) {
        long targetDirectionalAngle = landmarkDirectionalAngle + targetDirection - landmarkDirection;

        while (targetDirectionalAngle < 0) {
            targetDirectionalAngle += 1296000;
        }

        while (targetDirectionalAngle >= 1296000) {
            targetDirectionalAngle -= 1296000;
        }

        return targetDirectionalAngle;
    }

    /**
     * Defines the increment of coordinates along the X axis
     * @param targetDirectionalAngle directional angle of base->target line in seconds
     * @param inclinedDistance inclined distance in millimeters
     * @param tiltAngle tilt angle of base->target line in seconds
     * @return result in millimeters
     */
    @Override
    public long getDeltaX(long targetDirectionalAngle, long inclinedDistance, long tiltAngle) {
        double deltaX = cos(toRadians((double) targetDirectionalAngle / 3600)) *
                inclinedDistance *
                cos(toRadians((double) tiltAngle / 3600));
        return round(deltaX);
    }

    /**
     * Defines the increment of coordinates along the Y axis
     * @param targetDirectionalAngle directional angle of base->target line in seconds
     * @param inclinedDistance inclined distance in millimeters
     * @param tiltAngle tilt angle of base->target line in seconds
     * @return result in millimeters
     */
    @Override
    public long getDeltaY(long targetDirectionalAngle, long inclinedDistance, long tiltAngle) {
        double deltaY = sin(toRadians((double) targetDirectionalAngle / 3600)) *
                inclinedDistance *
                cos(toRadians((double) tiltAngle / 3600));
         return round(deltaY);
    }

    @Override
    public long getDeltaZ(long inclinedDistance, long tiltAngle) {
        double deltaZ = inclinedDistance * sin(toRadians((double) tiltAngle / 3600));
        return round(deltaZ);
    }
}

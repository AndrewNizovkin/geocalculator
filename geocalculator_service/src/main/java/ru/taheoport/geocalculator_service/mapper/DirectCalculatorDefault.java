package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import static java.lang.Math.*;

@Component
public class DirectCalculatorDefault implements DirectCalculator{
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

    @Override
    public long getDeltaX(long targetDirectionalAngle, long inclinedDistance, long tiltAngle) {
        double deltaX = cos(toRadians((double) targetDirectionalAngle / 3600)) *
                inclinedDistance *
                cos(toRadians((double) tiltAngle / 3600));
        return round(deltaX);
    }

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

package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.model.InverseTask;

import static java.lang.Math.*;
import static java.lang.Math.toDegrees;

@Component
public class InverseTaskCalculatorDefault implements InverseTaskCalculator{
    @Override
    public long getHorDistance(InverseTask inverseTask) {
        double result;
        result = sqrt(pow((double) (inverseTask.getBaseX() - inverseTask.getTargetX()), 2) +
                pow((double) (inverseTask.getBaseY() - inverseTask.getTargetY()), 2));

        return round(result);
    }

    @Override
    public long getInclinedDistance(InverseTask inverseTask) {
        double result;
        result = sqrt(pow((double) (inverseTask.getBaseX() - inverseTask.getTargetX()), 2) +
                pow((double) (inverseTask.getBaseY() - inverseTask.getTargetY()), 2) +
                pow((double) inverseTask.getBaseZ() - inverseTask.getTargetZ(), 2));
        return round(result);
    }

    @Override
    public long getDirection(InverseTask inverseTask) {
        double dir = 0.0;
        long dX = inverseTask.getTargetX() - inverseTask.getBaseX();
        long dY = inverseTask.getTargetY() - inverseTask.getBaseY();

        if (dX == 0) {
            if (dY > 0) {
                return round(toDegrees(PI / 2) * 3600);
            }
            if (dY < 0) {
                return round(toDegrees(PI + PI / 2) * 3600);
            }

        } else {
            dir = atan((double) dY / dX);
            if (dY == 0) {
                if (dX < 0) {
                    return round(toDegrees(PI) * 3600);
                } else {
                    return 0;
                }
            }
            if ( dY < 0) {
                if (dX < 0) {
                    dir += PI;
                } else {
                    dir += 2 * PI;
                }

            } else {
                if (dX < 0) {
                    dir += PI;
                }
            }
        }

        return round(toDegrees(dir) * 3600);
    }

    @Override
    public long getElevation(InverseTask inverseTask) {
        return inverseTask.getTargetZ() - inverseTask.getBaseZ();
    }

    @Override
    public long getTiltAngle(InverseTask inverseTask) {
        double tilt;
        long elevation = getElevation(inverseTask);
        long horDistance = getHorDistance(inverseTask);
        if (horDistance == 0) return 0;
        tilt = atan((double) elevation / horDistance);
        return round(toDegrees(tilt) * 3600);
    }
}

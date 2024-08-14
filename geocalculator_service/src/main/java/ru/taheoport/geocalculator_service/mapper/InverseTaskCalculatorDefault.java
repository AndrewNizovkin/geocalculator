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
        result = sqrt(pow((double) (inverseTask.getFirstX() - inverseTask.getSecondX()), 2) +
                pow((double) (inverseTask.getFirstY() - inverseTask.getSecondY()), 2));

        return round(result);
    }

    @Override
    public long getInclinedDistance(InverseTask inverseTask) {
        double result;
        result = sqrt(pow((double) (inverseTask.getFirstX() - inverseTask.getSecondX()), 2) +
                pow((double) (inverseTask.getFirstY() - inverseTask.getSecondY()), 2) +
                pow((double) inverseTask.getFirstZ() - inverseTask.getSecondZ(), 2));
        return round(result);
    }

    @Override
    public long getDirection(InverseTask inverseTask) {
        double dir = 0.0;
        long dX = inverseTask.getSecondX() - inverseTask.getFirstX();
        long dY = inverseTask.getSecondY() - inverseTask.getFirstY();

        if (dX == 0) {
            if (dY > 0) {
                return round(toDegrees(PI / 2) * 3600);
            }
            if (dY < 0) {
                return round(toDegrees(PI + PI / 2) * 3600);
            }

        } else {
            dir = Math.atan((double) dY / dX);
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
        return inverseTask.getSecondZ() - inverseTask.getFirstZ();
    }
}

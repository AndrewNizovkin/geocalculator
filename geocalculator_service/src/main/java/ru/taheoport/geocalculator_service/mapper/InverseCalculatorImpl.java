package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;

import static java.lang.Math.*;

/**
 * This class implements methods of interface InverseCalculator
 * @author Nizovkin A.V.
 */
@Component
public class InverseCalculatorImpl implements InverseCalculator {

    @Override
    public long getHorDistance(long baseX, long baseY, long targetX, long targetY) {
        double result;
        result = sqrt(pow((double) (targetX - baseX), 2) +
                pow((double) (targetY - baseY), 2));
        return round(result);

    }

    @Override
    public long getInclinedDistance(long baseX, long baseY, long baseZ, long targetX, long targetY, long targetZ) {
        double result;
        result = sqrt(pow((double) (targetX - baseX), 2) +
                pow((double) (targetY - baseY), 2) +
                pow((double) targetZ - baseZ, 2));
        return round(result);
    }

    @Override
    public long getDirection(long baseX, long baseY, long targetX, long targetY) {
        double dir = 0.0;
        long dX = targetX - baseX;
        long dY = targetY - baseY;

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
    public long getElevation(long baseZ, long targetZ) {
        return targetZ - baseZ;
    }

    @Override
    public long getTiltAngle(long baseX, long baseY, long baseZ, long targetX, long targetY, long targetZ) {
        double tilt;
        long elevation = getElevation(baseZ, targetZ);
        long horDistance = getHorDistance(baseX, baseY, targetX, targetY);

        if (horDistance == 0) return 0;

        tilt = atan((double) elevation / horDistance);
        return round(toDegrees(tilt) * 3600);
    }

}

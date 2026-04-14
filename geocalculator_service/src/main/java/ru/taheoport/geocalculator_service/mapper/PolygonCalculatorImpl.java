package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.model.BindType;
import ru.taheoport.geocalculator_service.model.PolygonStation;
import ru.taheoport.geocalculator_service.model.Residuals;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;

/**
 * This class provides methods for
 * adjusting geodetic polygon
 */
@Component
@RequiredArgsConstructor
public class PolygonCalculatorImpl implements PolygonCalculator{

    private final Residuals residuals;

    private final PolygonRepository polygonRepository;

    private final InverseCalculator inverseCalculator;

    private final DirectCalculator directCalculator;

    /**
     * Adjusts polygon
     */
    @Override
    public void adjustPolygon() {
        int sizePolygon = polygonRepository.size();

        PolygonStation baseA = polygonRepository.getStationById(0);
        PolygonStation baseB = polygonRepository.getStationById(1);
        PolygonStation baseC = polygonRepository.getStationById(sizePolygon - 2);
        PolygonStation baseD = polygonRepository.getStationById(sizePolygon - 1);

        switch (residuals.getBindType()) {

            case TT -> {
                setPerimeter(1, 3);
                baseA.setDirectionAngle(inverseCalculator.getDirection(
                        baseA.getStationX(),
                        baseA.getStationY(),
                        baseB.getStationX(),
                        baseB.getStationY()
                ));
                baseC.setDirectionAngle(inverseCalculator.getDirection(
                        baseC.getStationX(),
                        baseC.getStationY(),
                        baseD.getStationX(),
                        baseD.getStationY()
                ));
                setCorrectionHorAngle(1, sizePolygon - 2);


            }

        }














    }

    /**
     * Defines type of polygon binding to the reference geodetic network
     */
    @Override
    public void setBindType() {
        residuals.setBindType(BindType.ZZ);

        boolean statusA = polygonRepository.getStationById(0).isStatus();
        boolean statusB = polygonRepository.getStationById(1).isStatus();
        boolean statusC = polygonRepository.getStationById(polygonRepository.size() - 2).isStatus();
        boolean statusD = polygonRepository.getStationById(polygonRepository.size() - 1).isStatus();

        if (statusA && statusB && statusC && statusD) {
            residuals.setBindType(BindType.TT);
            return;
        }

        if (statusA && statusB && !statusC && statusD) {
            residuals.setBindType(BindType.TO);
            return;
        }

        if (statusA && !statusB && statusC && statusD) {
            residuals.setBindType(BindType.OT);
            return;
        }

        if (statusA && !statusB && !statusC && statusD) {
            residuals.setBindType(BindType.OO);
            return;
        }

        if (statusA && statusB && !statusC && !statusD) {
            residuals.setBindType(BindType.TZ);
            return;
        }


        if (!statusA && !statusB && statusC && statusD) {
            residuals.setBindType(BindType.ZT);
        }
    }

    /**
     * Defines the sum of the horizontal distance of the polygon
     * in the specified range of station indexes
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setPerimeter(int start, int end) {
        long sumDistance = 0;
        for (int i = start; i <= end; i++) {
            sumDistance += polygonRepository.getStationById(i).getHorDistance();
        }
        residuals.setPerimeter(sumDistance);
    }

    /**
     * Defines angle residuals and the correction to the horizontal angles
     * of the polygon in the specified range of station indexes
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setCorrectionHorAngle(int start, int end) {
        long actualDirectionAngle = polygonRepository.getStationById(start - 1).getDirectionAngle();

        for (int i = start; i <= end; i++) {
            actualDirectionAngle = actualDirectionAngle + polygonRepository.getStationById(i).getHorAngle() + 648000;
        }

        while (actualDirectionAngle >= 1296000) {
            actualDirectionAngle -= 1296000;
        }

        residuals.setAngle(actualDirectionAngle - polygonRepository.getStationById(end).getDirectionAngle());

        double correctionAngle = -1 * (double) residuals.getAngle() / (polygonRepository.size() - 2);

        for (int i = start; i <= end; i++) {
            polygonRepository.getStationById(i).setCorrectionHorAngle(correctionAngle);
        }
    }

    /**
     * Sets the direction angle for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setDirectionAngle(int start, int end) {
        long correctionAngle;
        double sign;
        long actualDirectionAngle;
        if (start < end) {
            for (int i = start; i <= end; i++) {
                sign = Math.signum(polygonRepository.getStationById(i).getCorrectionHorAngle());
                correctionAngle = (long) (sign * Math.round(Math.abs(polygonRepository.getStationById(i).getCorrectionHorAngle())));
                actualDirectionAngle = polygonRepository.getStationById(i - 1).getDirectionAngle() +
                        polygonRepository.getStationById(i).getHorAngle() +
                        648000 +
                        correctionAngle;
                while (actualDirectionAngle >= 1296000) {
                    actualDirectionAngle -= 1296000;
                }
                polygonRepository.getStationById(i).setDirectionAngle(actualDirectionAngle);
            }
        } else {
            for (int i = start; i >= end; i--) {
                actualDirectionAngle = polygonRepository.getStationById(i + 1).getDirectionAngle() -
                        polygonRepository.getStationById(i + 1).getHorAngle() - 648000;
                        while (actualDirectionAngle < 0) {
                            actualDirectionAngle += 1296000;
                }

            }
        }
    }

    /**
     * Sets the deltaX and deltaY for polygon stations
     * in the specified range of indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setDeltaXY(int start, int end) {

    }

    /**
     * Sets the correctionX and correctionY for polygon stations
     * in the specified range of indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setCorrectionXY(int start, int end) {

    }

    /**
     * Sets the correctionZ for polygon stations
     * in the specified range of indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setCorrectionZ(int start, int end) {

    }

    /**
     * Sets the final XYZ coordinates for polygon stations
     * in the specified range of indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setXYZ(int start, int end) {

    }
}

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
        setBindType();
        int sizePolygon = polygonRepository.size();

        PolygonStation baseA = polygonRepository.getStationById(0);
        PolygonStation baseB = polygonRepository.getStationById(1);
        PolygonStation baseC = polygonRepository.getStationById(sizePolygon - 2);
        PolygonStation baseD = polygonRepository.getStationById(sizePolygon - 1);

        switch (residuals.getBindType()) {

            case TT -> {
                setPerimeter(1, sizePolygon - 3);
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
                setDirectionAngle(1, sizePolygon - 3);
                setDeltaXY(1, sizePolygon - 3);
                setCorrectionXY(1, sizePolygon - 3);
                setCorrectionZ(1, sizePolygon - 3);
                setXYZ(2, sizePolygon - 3);
            }

            case TO -> {
                setPerimeter(1, sizePolygon - 2);
                baseA.setDirectionAngle(inverseCalculator.getDirection(
                        baseA.getStationX(),
                        baseA.getStationY(),
                        baseB.getStationX(),
                        baseB.getStationY()
                ));
                setDirectionAngle(1, sizePolygon - 2);
                setDeltaXY(1, sizePolygon - 2);
                setCorrectionXY(1, sizePolygon - 2);
                setCorrectionZ(1, sizePolygon - 2);
                setXYZ(2, sizePolygon - 2);
            }

            case OT -> {
                setPerimeter(0, sizePolygon - 3);
                baseC.setDirectionAngle(inverseCalculator.getDirection(
                        baseC.getStationX(),
                        baseC.getStationY(),
                        baseD.getStationX(),
                        baseD.getStationY()
                ));
                setDirectionAngle(sizePolygon - 3, 0);
                setDeltaXY(0, sizePolygon - 3);
                setCorrectionXY(0, sizePolygon - 3);
                setCorrectionZ(0, sizePolygon - 3);
                setXYZ(1, sizePolygon - 3);
            }

            case TZ -> {
                setPerimeter(1, sizePolygon - 2);
                baseA.setDirectionAngle(inverseCalculator.getDirection(
                        baseA.getStationX(),
                        baseA.getStationY(),
                        baseB.getStationX(),
                        baseB.getStationY()
                ));
                setDirectionAngle(1, sizePolygon - 2);
                setDeltaXY(1, sizePolygon - 2);
                setXYZ(2, sizePolygon - 1);
            }

            case ZT -> {
                setPerimeter(0, sizePolygon - 3);
                baseC.setDirectionAngle(inverseCalculator.getDirection(
                        baseC.getStationX(),
                        baseC.getStationY(),
                        baseD.getStationX(),
                        baseD.getStationY()
                ));
                setDirectionAngle(sizePolygon - 3, 0);
                setDeltaXY(0, sizePolygon - 3);
                setXYZ(sizePolygon - 3, 0);
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
        long actualDirectionAngle;
        if (start < end) {
            for (int i = start; i <= end; i++) {
                correctionAngle = doubleToLong(polygonRepository.getStationById(i).getCorrectionHorAngle());
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
                polygonRepository.getStationById(i).setDirectionAngle(actualDirectionAngle);



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
        PolygonStation station;

        for (int i = start; i <= end; i++) {
            station = polygonRepository.getStationById(i);
            station.setDeltaX(directCalculator.getDeltaX(
                    station.getDirectionAngle(),
                    station.getHorDistance(),
                    0
            ));
            station.setDeltaY(directCalculator.getDeltaY(
                    station.getDirectionAngle(),
                    station.getHorDistance(),
                    0
            ));
        }
    }

    /**
     * Defines linear residuals and
     * sets the correctionX and correctionY for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setCorrectionXY(int start, int end) {
        long sumDeltaX = 0;
        long sumDeltaY = 0;

        for (int i = start; i <= end; i++) {
            sumDeltaX += polygonRepository.getStationById(i).getDeltaX();
            sumDeltaY += polygonRepository.getStationById(i).getDeltaY();
        }

        residuals.setLinearX(
                polygonRepository.getStationById(start).getStationX() +
                        sumDeltaX -
                        polygonRepository.getStationById(end + 1).getStationX()
                );

        residuals.setLinearY(
                polygonRepository.getStationById(start).getStationY() +
                        sumDeltaY -
                        polygonRepository.getStationById(end + 1).getStationY()
        );

        residuals.setAbsolute(Math.round(Math.hypot(residuals.getLinearX(), residuals.getLinearY())));

        double denominator =  1 / ((double) residuals.getAbsolute() / (double) residuals.getPerimeter());

        residuals.setRelative("1:" + Math.round(denominator));

        double ratioX = -1 * (double) residuals.getLinearX() / (double) residuals.getPerimeter();
        double ratioY = -1 * (double) residuals.getLinearY() / (double) residuals.getPerimeter();

        for (int i = start; i <= end; i++) {
            PolygonStation station = polygonRepository.getStationById(i);
            station.setCorrectionX(doubleToLong(ratioX * (double) station.getHorDistance()));
            station.setCorrectionY(doubleToLong(ratioY * (double) station.getHorDistance()));
        }
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
        long sumElevation = 0;
        for (int i = start; i <= end; i++) {
            sumElevation += polygonRepository.getStationById(i).getElevation();
        }

        residuals.setElevation(polygonRepository.getStationById(start).getStationZ() +
                sumElevation - polygonRepository.getStationById(end + 1).getStationZ());

        double ratioZ = -1 * (double) residuals.getElevation() / (double) residuals.getPerimeter();

        for (int i = start; i <= end; i++) {
            PolygonStation station = polygonRepository.getStationById(i);
            station.setCorrectionZ(ratioZ * (double) station.getHorDistance());
        }
    }

    /**
     * Sets the final XYZ coordinates for polygon stations
     * in the specified range of indexes
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setXYZ(int start, int end) {
        PolygonStation backStation;
        PolygonStation station;

        if (start < end) {
            for (int i = start; i <= end; i++) {

                backStation = polygonRepository.getStationById(i - 1);
                station = polygonRepository.getStationById(i);

                station.setStationX(
                        backStation.getStationX() +
                                backStation.getDeltaX() +
                                backStation.getCorrectionX()
                );

                station.setStationY(
                        backStation.getStationY() +
                                backStation.getDeltaY() +
                                backStation.getCorrectionY()
                );

                station.setStationZ(
                        backStation.getStationZ() +
                                backStation.getElevation() +
                                doubleToLong(backStation.getCorrectionZ())
                );
            }
        } else {
            for (int i = start; i >= end; i--) {
                backStation = polygonRepository.getStationById(i + 1);
                station = polygonRepository.getStationById(i);

                station.setStationX(
                        backStation.getStationX() -
                                station.getDeltaX() -
                                station.getCorrectionX()
                );

                station.setStationY(
                        backStation.getStationY() -
                                station.getDeltaY()-
                                station.getCorrectionY()
                );

                station.setStationZ(
                        backStation.getStationZ() -
                                station.getElevation() -
                                doubleToLong(station.getCorrectionZ())
                );
            }

        }

    }

    /**
     * Converts double value to long
     * @param value double value
     * @return long value
     */
    @Override
    public long doubleToLong(double value) {
        double sign = Math.signum(value);
        value = Math.abs(value);
        return (long) sign * Math.round(value);
    }
}

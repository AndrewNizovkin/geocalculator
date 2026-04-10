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
     * Defines type of polygon binding to the reference geodetic network
     */
    @Override
    public void setBindType() {
        residuals.setBindType(BindType.ZZ);

        boolean baseA = polygonRepository.getStationById(0).isStatus();
        boolean baseB = polygonRepository.getStationById(1).isStatus();
        boolean baseC = polygonRepository.getStationById(polygonRepository.size() - 2).isStatus();
        boolean baseD = polygonRepository.getStationById(polygonRepository.size() - 1).isStatus();

        if (baseA && baseB && baseC && baseD) {
            residuals.setBindType(BindType.TT);
            return;
        }

        if (baseA && baseB && !baseC && baseD) {
            residuals.setBindType(BindType.TO);
            return;
        }

        if (baseA && !baseB && baseC && baseD) {
            residuals.setBindType(BindType.OT);
            return;
        }

        if (baseA && !baseB && !baseC && baseD) {
            residuals.setBindType(BindType.OO);
            return;
        }

        if (baseA && baseB && !baseC && !baseD) {
            residuals.setBindType(BindType.TZ);
            return;
        }


        if (!baseA && !baseB && baseC && baseD) {
            residuals.setBindType(BindType.ZT);
        }
    }

    /**
     * Defines the sum of the horizontal distance of the polygon
     * in the specified range of station indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setPerimeter(int start, int end) {

    }

    /**
     * Defines the correction to the horizontal angles of the polygon
     * in the specified range of station indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setCorrectionHorAngle(int start, int end) {

    }

    /**
     * Sets the direction angle for polygon stations
     * in the specified range of indexes
     *
     * @param start int beginning of the range
     * @param end   int end of range
     */
    @Override
    public void setDirectionAngle(int start, int end) {

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

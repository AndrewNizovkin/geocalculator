package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.DirectCalculator;
import ru.taheoport.geocalculator_service.mapper.InverseCalculator;
import ru.taheoport.geocalculator_service.mapper.PolygonCalculator;
import ru.taheoport.geocalculator_service.mapper.PolygonMapper;
import ru.taheoport.geocalculator_service.model.*;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;

import java.util.List;

/**
 * This class provides methods for processing and adjusting of
 * geodetic polygon
 */
@Service
@RequiredArgsConstructor
public class PolygonServiceImpl implements PolygonService{

    private final ValidResiduals validResiduals;

    private final Residuals residuals;

    private final PolygonRepository polygonRepository;

    private final PolygonMapper polygonMapper;

    private final PolygonCalculator polygonCalculator;

    /**
     * Gets general reports of processing and
     * adjusting of geodetic polygon
     *
     * @param polygonRequest list of string with geodetic data
     * @return list of strings
     */
    @Override
    public List<String> getPolygonReports(List<String> polygonRequest) {

        clearAll();

        boolean success = polygonMapper.polygonRequestToPolygon(polygonRequest);

        if (!success) return polygonMapper.getErrorResponse("Bad request!");

        if (polygonRepository.size() < 3) return polygonMapper.getErrorResponse("Few stations!");

        polygonCalculator.setBindType();
        if (residuals.getBindType() == BindType.ZZ) return polygonMapper.getErrorResponse("Unknown polygon binding scheme!");

        polygonCalculator.adjustPolygon();

        polygonMapper.clearReportResiduals();
        polygonMapper.setReportResiduals();

        return polygonMapper.polygonToPolygonResponse();
    }

    /**
     * Calculates geodetic polygon
     */
    @Override
    public void calculatePolygon() {
        int sizePolygon = polygonRepository.size();

        PolygonStation baseA = polygonRepository.getStationById(0);
        PolygonStation baseB = polygonRepository.getStationById(1);
        PolygonStation baseC = polygonRepository.getStationById(sizePolygon - 2);
        PolygonStation baseD = polygonRepository.getStationById(sizePolygon - 1);

//        baseA.setDirectionAngle();

    }

    /**
     * Clears all components
     */
    @Override
    public void clearAll() {
        polygonRepository.clearAll();

        residuals.setPerimeter(0);
        residuals.setElevation(0);
        residuals.setAngle(0);
        residuals.setLinearX(0);
        residuals.setLinearY(0);
        residuals.setAbsolute(0);
        residuals.setRelative("");
        residuals.setBindType(BindType.ZZ);

        validResiduals.setValidRelative("");
        validResiduals.setValidElevation(0);
        validResiduals.setValidAngle(0);
        validResiduals.setValidAbsolute("");
    }
}

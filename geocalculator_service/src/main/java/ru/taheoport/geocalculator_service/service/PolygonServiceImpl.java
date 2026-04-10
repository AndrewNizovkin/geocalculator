package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.DirectCalculator;
import ru.taheoport.geocalculator_service.mapper.InverseCalculator;
import ru.taheoport.geocalculator_service.mapper.PolygonCalculator;
import ru.taheoport.geocalculator_service.mapper.PolygonMapper;
import ru.taheoport.geocalculator_service.model.BindType;
import ru.taheoport.geocalculator_service.model.Residuals;
import ru.taheoport.geocalculator_service.model.ValidResiduals;
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
     * @param polygonResponse list of string with geodetic data
     * @return list of strings
     */
    @Override
    public List<String> getPolygonReports(List<String> polygonResponse) {

        boolean success = polygonMapper.polygonRequestToPolygon(
                polygonResponse,
                polygonRepository,
                validResiduals);

        if (!success) return polygonMapper.getErrorResponse("Bad request!");

        if (polygonRepository.size() < 3) return polygonMapper.getErrorResponse("Few stations!");

        polygonCalculator.setBindType();
        if (residuals.getBindType() == BindType.ZZ) return polygonMapper.getErrorResponse("Unknown polygon binding scheme!");

        //TODO



        return polygonMapper.polygonToPolygonResponse(
                polygonRepository,
                validResiduals,
                residuals
        );

    }

    /**
     * Calculates geodetic polygon
     */
    @Override
    public void calculatePolygon() {

    }
}

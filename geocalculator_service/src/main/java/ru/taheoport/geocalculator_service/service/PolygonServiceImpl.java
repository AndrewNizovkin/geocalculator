package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.DirectCalculator;
import ru.taheoport.geocalculator_service.mapper.InverseCalculator;
import ru.taheoport.geocalculator_service.mapper.PolygonMapper;
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

    private final InverseCalculator inverseCalculator;

    private final DirectCalculator directCalculator;

    private final ValidResiduals validResiduals;

    private final Residuals residuals;

    private final PolygonRepository polygonRepository;

    private final PolygonMapper polygonMapper;

    /**
     * Gets general reports of processing and
     * adjusting of geodetic polygon
     *
     * @param polygonResponse list of string with geodetic data
     * @return list of strings
     */
    @Override
    public List<String> getPolygonReports(List<String> polygonResponse) {

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonResponse,
                polygonRepository,
                validResiduals);

        if (access) {
            calculatePolygon();
            return polygonMapper.polygonToPolygonResponse(
                    polygonRepository,
                    validResiduals,
                    residuals
            );
        } else {
            return polygonMapper.getErrorResponse("Bad request");
        }
    }

    /**
     * Calculates geodetic polygon
     */
    @Override
    public void calculatePolygon() {

    }
}

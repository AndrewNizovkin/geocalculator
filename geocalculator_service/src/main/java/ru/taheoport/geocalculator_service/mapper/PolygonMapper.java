package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.model.Residuals;
import ru.taheoport.geocalculator_service.model.ValidResiduals;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;

import java.util.List;

/**
 * This interface defines methods for
 *  converting external data to and from the model format.
 */
public interface PolygonMapper {

    /**
     * Extract data from polygonRequest and fills polygon model
     * @param polygonRequest list of string with data
     * @param polygonRepository polygon station repository
     * @param validResiduals instance of ValidResiduals
     * @return true if polygonRequest contains valid geodetic data
     */
    boolean polygonRequestToPolygon(
            List<String> polygonRequest,
            PolygonRepository polygonRepository,
            ValidResiduals validResiduals
    );

    /**
     * Creates response contains processing reports
     * @param polygonRepository polygon station repository
     * @param validResiduals instance of ValidResiduals
     * @param residuals instance of Residuals
     * @return List of strings
     */
    List<String> polygonToPolygonResponse(
            PolygonRepository polygonRepository,
            ValidResiduals validResiduals,
            Residuals residuals
    );

    /**
     * Creates error response
     * @param message string message
     * @return list of strings
     */
    List<String> getErrorResponse(String message);
}

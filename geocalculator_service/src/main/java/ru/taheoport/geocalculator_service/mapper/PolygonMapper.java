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
     * @return true if polygonRequest contains valid geodetic data
     */
    boolean polygonRequestToPolygon(List<String> polygonRequest);

    /**
     * Creates response contains processing reports
     * @return List of strings
     */
    List<String> polygonToPolygonResponse();

    /**
     * Creates error response
     * @param message string message
     * @return list of strings
     */
    List<String> getErrorResponse(String message);

    /**
     * Sets blank values for reportResiduals
     */
    void clearReportResiduals();

    /**
     * Sets report residuals
     */
    void setReportResiduals();
}

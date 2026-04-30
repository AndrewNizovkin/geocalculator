package ru.taheoport.geocalculator_service.mapper;

import java.util.List;

/**
 * This interface defines methods for
 *  converting external data to and from the model format.
 */
public interface ExtractMapper {

    /**
     * Extract data from polygonRequest and fills Extraction model
     * @param extractRequest list of string with geodetic data
     * @return true if extractRequest contains valid geodetic data
     */
    boolean extractRequestToExtraction(List<String> extractRequest);

    /**
     * Creates response contains processing reports
     * @return list of strings
     */
    List<String> extractionToExtractResponse();

    /**
     * Creates error response
     * @param message string message
     * @return list of strings
     */
    List<String> getErrorResponse(String message);

}

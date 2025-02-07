package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.*;

/**
 * This interface defines methods for converting from
 * DirectTaskDto request too DirectTaskDto response
 */
public interface DirectTaskMapper {

    /**
     * Sets the calculated fields of an instance of DirectTaskDto
     * @param directTaskRequest Instance of DirectTaskResponse
     * @return Instance of InverseTaskResponse
     */
    DirectTaskResponse toDirectTaskResponse(DirectTaskRequest directTaskRequest);

    /**
     * Sets the calculated fields of an instance of DirectTaskFullResponse
     * @param directTaskRequest Instance of DirectTaskDto
     * @return Instance of InverseTaskResponse
     */
    DirectTaskFullResponse toDirectTaskFullResponse(DirectTaskRequest directTaskRequest);

    /**
     * Converts DirectStringRequest to DirectTaskRequest
     * @param directStringRequest DirectStringRequest
     * @return DirectTaskRequest
     */
    DirectTaskRequest toDirectTaskRequest(DirectStringRequest directStringRequest);

    /**
     * Converts the DirectTaskFullResponse to DirectStringResponse
     * @param directTaskFullResponse DirectTaskFullResponse
     * @return DirectStringResponse
     */
    DirectStringResponse toDirectStringResponse(DirectTaskFullResponse directTaskFullResponse);

}

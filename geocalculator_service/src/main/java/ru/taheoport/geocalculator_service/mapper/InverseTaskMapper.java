package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.*;

/**
 * This interface defines methods for converting from
 * InverseTaskDto request too InverseTaskDto response
 */
public interface InverseTaskMapper {

    /**
     * Sets the calculated fields of an instance of InverseTaskDto
     * @param inverseTaskRequest Instance of InverseTask
     * @return Instance of InverseTaskResponse
     */
    InverseTaskResponse toInverseTaskResponse(InverseTaskRequest inverseTaskRequest);

    /**
     * Sets the calculated fields of an instance of InverseTaskDto
     * @param inverseTaskRequest Instance of InverseTask
     * @return Instance of InverseTaskFullResponse
     */
    InverseTaskFullResponse toInverseTaskFullResponse(InverseTaskRequest inverseTaskRequest);

    /**
     * Converts the correct InverseStringRequest to InverseTaskRequest
     * @param inverseStringRequest InverseStringRequest
     * @return InverseTaskRequest
     */
    InverseTaskRequest toInverseTaskRequest(InverseStringRequest inverseStringRequest);

    /**
     * Converts the InverseTaskFullResponse to InverseStringResponse
     * @param inverseTaskFullResponse InverseTaskFullResponse
     * @return InverseStringResponse
     */
    InverseStringResponse toInverseStringResponse(InverseTaskFullResponse inverseTaskFullResponse);

}

package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;

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

}

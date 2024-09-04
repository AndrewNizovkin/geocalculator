package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;

/**
 * This interface defines methods for converting from
 * DirectTaskDto request too DirectTaskDto response
 */
public interface DirectTaskMapper {

    /**
     * Sets the calculated fields of an instance of DirectTaskDto
     * @param directTaskRequest Instance of DirectTaskDto
     * @return Instance of InverseTaskResponse
     */
    DirectTaskResponse toDirectTaskResponse(DirectTaskRequest directTaskRequest);
}

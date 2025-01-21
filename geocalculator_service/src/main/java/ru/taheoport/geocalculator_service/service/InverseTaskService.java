package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;

/**
 * This interface defines methods for solving Inverse Geodetic Task
 */
public interface InverseTaskService {

    /**
     * Solves the inverse geodesic problem,
     * update fields of instance InverseTaskDto
     * @param inverseTaskRequest instance of InverseTaskDto
     * @return instance of InverseTaskResponse
     */
    InverseTaskResponse solveInverseTask(InverseTaskRequest inverseTaskRequest);

    /**
     * Solves the inverse geodesic problem,
     * update fields of instance InverseTaskDto
     * @param inverseTaskRequest instance of InverseTaskDto
     * @return instance of InverseTaskFullResponse
     */
    InverseTaskFullResponse getInverseTaskFullResponse(InverseTaskRequest inverseTaskRequest);


}

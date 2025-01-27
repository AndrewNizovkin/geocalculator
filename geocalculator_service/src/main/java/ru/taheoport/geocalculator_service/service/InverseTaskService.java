package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.*;

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

    /**
     * Solves the inverse geodesic problem,
     * update fields of instance InverseTaskDto
     * @param inverseStringRequest instance of InverseTaskDto
     * @return instance of InverseStringResponse
     */
    InverseStringResponse getInverseStringResponse(InverseStringRequest inverseStringRequest);


}

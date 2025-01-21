package ru.taheoport.geocalculator_web.service;

import ru.taheoport.geocalculator_web.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_web.dto.InverseTaskResponse;

public interface InverseTaskService {

    /**
     * Solves inverse geodetic task
     * @param inverseTaskRequest InverseTaskRequest
     * @return InverseTaskResponse
     */
    InverseTaskResponse solveInverseTask(InverseTaskRequest inverseTaskRequest);
}

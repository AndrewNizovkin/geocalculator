package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;

/**
 * This interface defines method fo solving direct geodetic problem
 */
public interface DirectTaskService {

    /**
     * Solves the direct geodesic problem,
     * update fields of instance DirectTaskDto
     * @param directTaskRequest instance of DirectTaskDto
     * @return instance of DirectTaskResponse
     */
    DirectTaskResponse solveDirectTask(DirectTaskRequest directTaskRequest);
}

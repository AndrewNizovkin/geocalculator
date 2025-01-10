package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.DirectTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;

import java.util.List;

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

    /**
     * Solves the direct geodesic problem fo list,
     * update fields of list element
     * @param directTaskRequests list of DirectTaskRequest
     * @return List of instance of DirectTaskFullResponse
     */
    List<DirectTaskFullResponse> getDirectTaskFullResponse(List<DirectTaskRequest> directTaskRequests);
}

package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;

import java.util.List;

/**
 * This interface define method for solving of geodetic Potenot Problem
 * @author Nizovkin A.V.
 */
public interface PotenotService {

    /**
     * Solves geodetic Potenot Problem
     * @param potenotTaskRequestList List of landmark points with known coordinates
     * @return Instance of PotenotTaskDto with coordinates calculated point and it's accuracy
     */
    PotenotTaskResponse resolvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList);

    /**
     * Solves geodetic Potenot Problem
     * @param potenotStringRequests List of landmark points with known coordinates
     * @return Instance of PotenotTaskDto with coordinates calculated point and it's accuracy
     */
    PotenotStringResponse getPotenotStringResponse(List<PotenotStringRequest> potenotStringRequests);

    /**
     * Gives response if raw data is not valid
     * @return PotenotStringResponse
     */
    PotenotStringResponse getPotenotStringErrorResponse();
}

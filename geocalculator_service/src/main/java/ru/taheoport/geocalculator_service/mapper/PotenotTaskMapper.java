package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;

import java.util.List;

public interface PotenotTaskMapper {

    /**
     * Solves Potenot geodetic task
     * Determines the coordinates of the station in angular directions to landmarks with known coordinates
     * @param potenotTaskRequestList
     * @return
     */
    PotenotTaskResponse solvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList);

    /**
     * Converts instance of PotenotStringRequest to instance of PotenotTaskRequest
     * @param potenotStringRequest instance of PotenotStringRequest
     * @return instanse of PotenotTaskRequest
     */
    PotenotTaskRequest toPotenotTaskRequest(PotenotStringRequest potenotStringRequest);

    /**
     * Converts list of PotenotStringRequest to list of PotenotTaskRequest
     * @param potenotStringRequests list of PotenotStringRequest
     * @return list of PotenotTaskRequest
     */
    List<PotenotTaskRequest> toPotenotTaskRequests(List<PotenotStringRequest> potenotStringRequests);

    /**
     * Converts PotenotTaskResponse to PotenotStringResponse
     * @param potenotTaskResponse contains target coordinates in millimeters
     * @return PotenotStringResponse with target coordinates in meters
     */
    PotenotStringResponse toPotenotStringResponse(PotenotTaskResponse potenotTaskResponse);


//    PotenotStringResponse to


}

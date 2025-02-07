package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.*;
import ru.taheoport.geocalculator_service.mapper.DirectTaskMapper;

import java.util.List;

/**
 * This class implements interface DirectTaskService
 */
@Component
@RequiredArgsConstructor
public class DirectTaskServiceImpl implements DirectTaskService{

    private final DirectTaskMapper directTaskMapper;

    /**
     * Solves the direct geodesic problem,
     * update fields of instance DirectTaskDto
     * @param directTaskRequest instance of DirectTaskDto
     * @return instance of DirectTaskResponse
     */
    @Override
    public DirectTaskResponse solveDirectTask(DirectTaskRequest directTaskRequest) {

        return directTaskMapper.toDirectTaskResponse(directTaskRequest);
    }

    /**
     * Solves the direct geodesic problem fo list,
     * update fields of list element
     * @param directTaskRequests list of DirectTaskRequest
     * @return List of instance of DirectTaskFullResponse
     */
    @Override
    public List<DirectTaskFullResponse> getDirectTaskFullResponse(List<DirectTaskRequest> directTaskRequests) {
        return directTaskRequests.stream().map(directTaskMapper::toDirectTaskFullResponse).toList();
    }

    /**
     * Solves the direct geodesic problem fo list,
     * update fields of list element
     * @param directStringRequest list of DirectTaskRequest
     * @return List of instance of DirectTaskFullResponse
     */
    @Override
    public DirectStringResponse getDirectStringResponse(DirectStringRequest directStringRequest) {
        DirectTaskRequest directTaskRequest = directTaskMapper.toDirectTaskRequest(directStringRequest);
        DirectTaskFullResponse directTaskFullResponse = directTaskMapper.toDirectTaskFullResponse(directTaskRequest);
        return directTaskMapper.toDirectStringResponse(directTaskFullResponse);
    }

    /**
     * Gives response if raw data is not valid
     * @return DirectStringResponse
     */
    @Override
    public DirectStringResponse getDirectStringErrorResponse() {
        DirectStringResponse directStringResponse = new DirectStringResponse();
        directStringResponse.setTargetX("0.000");
        directStringResponse.setTargetY("0.000");
        directStringResponse.setTargetZ("0.000");
        return directStringResponse;

    }
}

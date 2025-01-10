package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.DirectTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
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
}

package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.dto.*;
import ru.taheoport.geocalculator_service.mapper.InverseTaskMapper;

@Service
@RequiredArgsConstructor
public class InverseTaskServiceImpl implements InverseTaskService {

    private final InverseTaskMapper inverseTaskMapper;


    @Override
    public InverseTaskResponse solveInverseTask(InverseTaskRequest inverseTaskRequest) {
        return inverseTaskMapper.toInverseTaskResponse(inverseTaskRequest);
    }

    /**
     * Solves the inverse geodesic problem,
     * update fields of instance InverseTaskDto
     * @param inverseTaskRequest instance of InverseTaskDto
     * @return instance of InverseTaskFullResponse
     */
    @Override
    public InverseTaskFullResponse getInverseTaskFullResponse(InverseTaskRequest inverseTaskRequest) {
        return inverseTaskMapper.toInverseTaskFullResponse(inverseTaskRequest);
    }

    /**
     * Solves the inverse geodesic problem,
     * update fields of instance InverseTaskDto
     * @param inverseStringRequest instance of InverseTaskDto
     * @return instance of InverseStringResponse
     */
    @Override
    public InverseStringResponse getInverseStringResponse(InverseStringRequest inverseStringRequest) {

        //TODO: checks raw data

        InverseTaskRequest inverseTaskRequest = inverseTaskMapper.toInverseTaskRequest(inverseStringRequest);
        InverseTaskFullResponse inverseTaskFullResponse = inverseTaskMapper.toInverseTaskFullResponse(inverseTaskRequest);

        return inverseTaskMapper.toInverseStringResponse(inverseTaskFullResponse);
    }
}


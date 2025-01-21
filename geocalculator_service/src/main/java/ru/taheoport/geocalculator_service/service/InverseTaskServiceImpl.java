package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;
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
}


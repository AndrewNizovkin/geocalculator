package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import ru.taheoport.geocalculator_service.mapper.DirectTaskMapper;

/**
 * This class implements interface DirectTaskService
 */
@Component
@RequiredArgsConstructor
public class DirectTaskServiceImpl implements DirectTaskService{

    private final DirectTaskMapper directTaskMapper;
    @Override
    public DirectTaskResponse solveDirectTask(DirectTaskRequest directTaskRequest) {

        return directTaskMapper.toDirectTaskResponse(directTaskRequest);
    }
}

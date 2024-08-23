package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.DirectTaskDto;
import ru.taheoport.geocalculator_service.mapper.DirectTaskMapper;

/**
 * This class implements interface DirectTaskService
 */
@Component
@RequiredArgsConstructor
public class DirectTaskServiceImpl implements DirectTaskService{

    private final DirectTaskMapper directTaskMapper;
    @Override
    public DirectTaskDto solveDirectTask(DirectTaskDto directTaskDto) {

        return directTaskMapper.toDirectTaskDto(directTaskDto);
    }
}

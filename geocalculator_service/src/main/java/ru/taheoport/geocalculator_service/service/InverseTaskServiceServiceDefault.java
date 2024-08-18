package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.mapper.InverseTaskMapper;

@Service
@RequiredArgsConstructor
public class InverseTaskServiceServiceDefault implements InverseTaskService {

    private final InverseTaskMapper inverseTaskMapper;


    @Override
    public InverseTaskDto solveInverseTask(InverseTaskDto inverseTaskDto) {
        return inverseTaskMapper.toInverseTaskDto(inverseTaskDto);
    }
}


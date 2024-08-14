package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.mapper.InverseTaskMapper;
import ru.taheoport.geocalculator_service.model.InverseTask;
import ru.taheoport.geocalculator_service.repository.InverseTaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InverseTaskServiceServiceDefault implements InverseTaskService {

    private final InverseTaskRepository inverseTaskRepository;
    private final InverseTaskMapper inverseTaskMapper;
    private static final String NOT_FOUND_POINT_MESSAGE = "Couldn't find the task with the id = ";

    @Override
    public List<InverseTaskDto> findAll() {
        return inverseTaskMapper.toListInverseTaskDto(inverseTaskRepository.findAll());
    }

    @Override
    public InverseTaskDto createInverseTask(InverseTaskDto inverseTaskDto) {
        InverseTask inverseTask = inverseTaskMapper.toInverseTask(inverseTaskDto);
        return inverseTaskMapper.toInverseTaskDto(inverseTaskRepository.saveAndFlush(inverseTask));
    }

    @Override
    public InverseTaskDto findById(long id) {
        InverseTask inverseTask = inverseTaskRepository.findById(id).orElse(null);
        if (inverseTask != null) {
            return inverseTaskMapper.toInverseTaskDto(inverseTask);
        } else {
            throw new RuntimeException(NOT_FOUND_POINT_MESSAGE + id);
        }
    }

}


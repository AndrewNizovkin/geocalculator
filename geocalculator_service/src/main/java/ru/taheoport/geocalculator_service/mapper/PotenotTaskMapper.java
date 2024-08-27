package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.PotenotTaskDto;

import java.util.List;

public interface PotenotTaskMapper {

    PotenotTaskDto solvePotenotTask(List<PotenotTaskDto> potenotTaskDtoList);
}

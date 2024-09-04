package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;

import java.util.List;

public interface PotenotTaskMapper {

    PotenotTaskResponse solvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList);
}

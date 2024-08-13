package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.model.InverseTask;

import java.util.List;

/**
 * This interface defines methods for converting between InverseTask and InverseTaskDto
 */
public interface InverseTaskMapper {

    /**
     * Converts instance of InverseTask to instance of InverseTaskDto
     * @param inverseTask Instance of InverseTask
     * @return Instance of InverseTaskDto
     */
    InverseTaskDto toInverseTaskDto(InverseTask inverseTask);

    /**
     * Converts instance of InverseTaskDto to instance of InverseTask
     * @param inverseTaskDto Instance of InverseTaskDto
     * @return Instance of InverseTask
     */
    InverseTask toInverseTask(InverseTaskDto inverseTaskDto);

    /**
     * Converts list of InverseTask to list of InverseTaskDto
     * @return List of InverseTaskDto
     */
    List<InverseTaskDto> toListInverseTaskDto(List<InverseTask> inverseTasks);
}

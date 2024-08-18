package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.InverseTaskDto;

/**
 * This interface defines methods for converting between InverseTask and InverseTaskDto
 */
public interface InverseTaskMapper {

    /**
     * Converts instance of InverseTask to instance of InverseTaskDto
     * @param inverseTaskDto Instance of InverseTask
     * @return Instance of InverseTaskDto
     */
    InverseTaskDto toInverseTaskDto(InverseTaskDto inverseTaskDto);

}

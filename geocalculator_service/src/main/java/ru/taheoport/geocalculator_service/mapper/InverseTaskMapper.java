package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.InverseTaskDto;

/**
 * This interface defines methods for converting from
 * InverseTaskDto request too InverseTaskDto response
 */
public interface InverseTaskMapper {

    /**
     * Sets the calculated fields of an instance of InverseTaskDto
     * @param inverseTaskDto Instance of InverseTask
     * @return Instance of InverseTaskDto
     */
    InverseTaskDto toInverseTaskDto(InverseTaskDto inverseTaskDto);

}

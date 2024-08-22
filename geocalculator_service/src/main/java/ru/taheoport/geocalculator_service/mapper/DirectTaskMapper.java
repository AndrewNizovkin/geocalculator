package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.dto.DirectTaskDto;

/**
 * This interface defines methods for converting from
 * DirectTaskDto request too DirectTaskDto response
 */
public interface DirectTaskMapper {

    /**
     * Sets the calculated fields of an instance of DirectTaskDto
     * @param directTaskDto Instance of DirectTaskDto
     * @return Instance of InverseTaskDto
     */
    DirectTaskDto toDirectTaskDto(DirectTaskDto directTaskDto);
}

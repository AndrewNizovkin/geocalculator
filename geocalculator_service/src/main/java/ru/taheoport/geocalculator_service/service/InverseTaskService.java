package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.model.InverseTask;

import java.util.List;

/**
 * This interface defines methods for service layer
 */
public interface InverseTaskService {

    /**
     * Finds all InverseTask from db
     * @return List of instance of InverseTaskDto
     */
    List<InverseTaskDto> findAll();

    /**
     * Create new record in db
     * @param inverseTaskDto instance of InverseTaskDto
     * @return instance of InverseTaskDto
     */
    InverseTaskDto createInverseTask(InverseTaskDto inverseTaskDto);

    /**
     * Finds InverseTask by Id
     * @param id long id
     * @return instance of InverseTaskDto
     */
    InverseTaskDto findById(long id);


}

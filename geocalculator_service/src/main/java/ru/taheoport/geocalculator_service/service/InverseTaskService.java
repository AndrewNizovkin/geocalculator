package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.InverseTaskDto;

/**
 * This interface defines methods for service layer
 */
public interface InverseTaskService {

    /**
     * Solves the inverse geodesic problem,
     * update fields of instance InverseTaskDto
     * @param inverseTaskDto instance of InverseTaskDto
     * @return instance of InverseTaskDto
     */
    InverseTaskDto solveInverseTask(InverseTaskDto inverseTaskDto);



}

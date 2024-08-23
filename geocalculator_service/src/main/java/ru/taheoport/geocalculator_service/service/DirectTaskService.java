package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.DirectTaskDto;

/**
 * This interface defines method fo solving direct geodetic problem
 */
public interface DirectTaskService {

    /**
     * Solves the direct geodesic problem,
     * update fields of instance DirectTaskDto
     * @param directTaskDto instance of DirectTaskDto
     * @return instance of DirectTaskDto
     */
    DirectTaskDto solveDirectTask(DirectTaskDto directTaskDto);
}

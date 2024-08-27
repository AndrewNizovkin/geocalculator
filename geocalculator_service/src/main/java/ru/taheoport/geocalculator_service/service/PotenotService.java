package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.dto.PotenotTaskDto;

import java.util.List;

/**
 * This interface define method for solving of geodetic Potenot Problem
 * @author Nizovkin A.V.
 */
public interface PotenotService {

    /**
     * Solves geodetic Potenot Problem
     * @param potenotTaskDtoList List of landmark points with known coordinates
     * @return Instance of PotenotTaskDto with coordinates calculated point and it's accuracy
     */
    PotenotTaskDto resolvePotenotTask(List<PotenotTaskDto> potenotTaskDtoList);
}

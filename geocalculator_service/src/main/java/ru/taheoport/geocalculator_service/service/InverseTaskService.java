package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.model.InverseTask;

import java.util.List;

/**
 * This interface defines methods for solving the inverse geodesic task
 */
public interface InverseTaskService {

    /**
     * Gets all InverseTaskRequests from db
     * @return
     */
    List<InverseTask> findAll();

    /**
     * Gets horizontal distance between too points with 2D coordinates
     * @param inverseTask instance of BackTaskRequest
     * @return double result in millimeters
     */
    long getHorDistance(InverseTask inverseTask);

    /**
     * Gets inclined distance between too points with 3D coordinates
     * @param inverseTask instance of BackTaskRequest
     * @return double result millimeters
     */
    long getInclinedDistance(InverseTask inverseTask);

    /**
     * Gets directional angle first-second line
     * @param inverseTask instance of BackTackRequest
     * @return result in seconds
     */
    long getDirection(InverseTask inverseTask);

}

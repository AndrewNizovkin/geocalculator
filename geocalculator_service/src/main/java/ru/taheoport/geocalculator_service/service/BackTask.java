package ru.taheoport.geocalculator_service.service;

import ru.taheoport.geocalculator_service.model.BackTaskRequest;

public interface BackTask {

    /**
     * Gets horizontal distance between too points
     * @param backTaskRequest instance of BackTaskRequest
     * @return double result in millimeters
     */
    long getHorDistance(BackTaskRequest backTaskRequest);

    /**
     * Gets inclined distance between too points
     * @param backTaskRequest instance of BackTaskRequest
     * @return double result millimeters
     */
    long getInclinedDistance(BackTaskRequest backTaskRequest);

    /**
     * Gets directional angle first-second line
     * @param backTaskRequest instance of BackTackRequest
     * @return result in seconds
     */
    double getDirection(BackTaskRequest backTaskRequest);
}

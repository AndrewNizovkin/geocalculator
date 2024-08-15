package ru.taheoport.geocalculator_service.mapper;

import ru.taheoport.geocalculator_service.model.InverseTask;

/**
 * This interface defines methods for solving the inverse geodesic task
 */
public interface InverseTaskCalculator {
    /**
     * Gets horizontal distance between too points with 2D coordinates
     * @param inverseTask instance of InverseTask
     * @return double result in millimeters
     */
    long getHorDistance(InverseTask inverseTask);

    /**
     * Gets inclined distance between too points with 3D coordinates
     * @param inverseTask instance of InverseTask
     * @return double result millimeters
     */
    long getInclinedDistance(InverseTask inverseTask);

    /**
     * Gets directional angle base->target line
     * @param inverseTask instance of InverseTask
     * @return result in seconds
     */
    long getDirection(InverseTask inverseTask);

    /**
     * Gets height difference between the target and the base
     * @param inverseTask instance of InverseTask
     * @return result in millimeters
     */
    long getElevation(InverseTask inverseTask);

    /**
     * Gets tilt angle base->target line
     * @param inverseTask instance of InverseTask
     * @return result in seconds
     */
    long getTiltAngle(InverseTask inverseTask);

}

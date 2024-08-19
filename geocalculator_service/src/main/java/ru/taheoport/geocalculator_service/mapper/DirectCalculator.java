package ru.taheoport.geocalculator_service.mapper;


/**
* This interface defines methods for solving the direct geodesic task
*/
public interface DirectCalculator {

    /**
     * Defines the directional angle of the direction to the target
     * @param landmarkDirectionalAngle directional angle of the direction to the landmark in seconds
     * @param landmarkDirection direction to landmark in seconds
     * @param targetDirection direction to target in seconds
     * @return result in seconds
     */
    long getDirectionalAngle(long landmarkDirectionalAngle, long landmarkDirection, long targetDirection);

    /**
     * Defines the increment of coordinates along the X axis
     * @param targetDirectionalAngle directional angle of base->target line in seconds
     * @param inclinedDistance inclined distance in millimeters
     * @param tiltAngle tilt angle of base->target line in seconds
     * @return result in millimeters
     */
    long getDeltaX(long targetDirectionalAngle, long inclinedDistance, long tiltAngle);

    /**
     * Defines the increment of coordinates along the Y axis
     * @param targetDirectionalAngle directional angle of base->target line in seconds
     * @param inclinedDistance inclined distance in millimeters
     * @param tiltAngle tilt angle of base->target line in seconds
     * @return result in millimeters
     */
    long getDeltaY(long targetDirectionalAngle, long inclinedDistance, long tiltAngle);

    /**
     * Defines the increment of coordinates along the Z axis
     * @param inclinedDistance inclined distance in millimeters
     * @param tiltAngle tilt angle of base->target line in seconds
     * @return result in millimeters
     */
    long getDeltaZ(long inclinedDistance, long tiltAngle);


}

package ru.taheoport.geocalculator_service.mapper;

/**
 * This interface defines methods for solving the inverse geodesic task
 */
public interface InverseCalculator {

    /**
     * Gets horizontal distance between too points with 2D coordinates
     * @param baseX Coordinate X of base point in millimeters
     * @param baseY Coordinate Y of base point in millimeters
     * @param targetX Coordinate X of target point in millimeters
     * @param targetY Coordinate Y of target point in millimeters
     * @return long result in millimeters
     */
    long getHorDistance(long baseX,
                        long baseY,
                        long targetX,
                        long targetY);

    /**
     * Gets inclined distance between too points with 3D coordinates
     * @param baseX Coordinate X of base point in millimeters
     * @param baseY Coordinate Y of base point in millimeters
     * @param baseZ Coordinate Z of base point in millimeters
     * @param targetX Coordinate X of target point in millimeters
     * @param targetY Coordinate Y of target point in millimeters
     * @param targetZ Coordinate Y of target point in millimeters
     * @return double result in millimeters
     */
    long getInclinedDistance(long baseX,
                             long baseY,
                             long baseZ,
                             long targetX,
                             long targetY,
                             long targetZ);

    /**
     * Gets directional angle base->target line
     * @param baseX Coordinate X of base point in millimeters
     * @param baseY Coordinate Y of base point in millimeters
     * @param targetX Coordinate X of target point in millimeters
     * @param targetY Coordinate Y of target point in millimeters
     * @return result in seconds
     */
    long getDirection(long baseX,
                      long baseY,
                      long targetX,
                      long targetY);

    /**
     * Gets height difference between the target and the base
     * @param baseZ Coordinate Z of base point in millimeters
     * @param targetZ Coordinate Y of target point in millimeters
     * @return result in millimeters
     */
    long getElevation(long baseZ, long targetZ);


    /**
     * Gets tilt angle base->target line
     * @param baseX Coordinate X of base point in millimeters
     * @param baseY Coordinate Y of base point in millimeters
     * @param baseZ Coordinate Z of base point in millimeters
     * @param targetX Coordinate X of target point in millimeters
     * @param targetY Coordinate Y of target point in millimeters
     * @param targetZ Coordinate Y of target point in millimeters
     * @return result in seconds
     */
    long getTiltAngle(long baseX,
                      long baseY,
                      long baseZ,
                      long targetX,
                      long targetY,
                      long targetZ);

}

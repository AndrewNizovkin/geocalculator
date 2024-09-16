package ru.taheoport.geocalculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class encapsulates result of solving inverse geodetic task
 * @author Nizovkin A.V.
 */
@Data
@NoArgsConstructor
public class InverseTaskResponse {

    /**
     * The angle between the direction to the north and
     * the direction to the target, in seconds
     */
    private long direction;

    /**
     * Horizontal distance between base and target, in millimeters
     */
    private long horDistance;

    /**
     * Inclined distance between base and target, in millimeters
     */
    private long inclinedDistance;

    /**
     * The tilt angle of base->target line, in seconds
     */
    private long tiltAngle;

    /**
     * The height difference between the target and the base
     */
    private long elevation;

//    /**
//     * Constructor with all arguments
//     * @param direction Directional angle base to target
//     * @param horDistance Horizontal distance between base and target
//     * @param inclinedDistance Inclined distance between base and target
//     * @param tiltAngle Tilt angle of line base to target
//     * @param elevation The height difference between the base and the target
//     */
//    public InverseTaskResponse(
//            long direction,
//            long horDistance,
//            long inclinedDistance,
//            long tiltAngle,
//            long elevation) {
//        this.direction = direction;
//        this.horDistance = horDistance;
//        this.inclinedDistance = inclinedDistance;
//        this.tiltAngle = tiltAngle;
//        this.elevation = elevation;
//    }
}

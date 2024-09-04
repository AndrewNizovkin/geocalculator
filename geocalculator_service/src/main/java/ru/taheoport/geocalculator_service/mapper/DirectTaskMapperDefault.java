package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;

/**
 * This class implements interface DirectTaskMapper
 */
@Component
@RequiredArgsConstructor
public class DirectTaskMapperDefault implements DirectTaskMapper{

    private final DirectCalculator directCalculator;
    private final Inverse inverseCalculator;

    @Override
    public DirectTaskResponse toDirectTaskResponse(DirectTaskRequest directTaskRequest) {

        DirectTaskResponse directTaskResponse = new DirectTaskResponse();

        long landmarkDirectionalAngle = inverseCalculator.getDirection(
                directTaskRequest.getBaseX(),
                directTaskRequest.getBaseY(),
                directTaskRequest.getLandmarkX(),
                directTaskRequest.getLandmarkY()
        );

        long targetDirectionAngle = directCalculator.getDirectionalAngle(
                landmarkDirectionalAngle,
                directTaskRequest.getLandmarkDirection(),
                directTaskRequest.getTargetDirection()
        );

        long deltaX = directCalculator.getDeltaX(
                targetDirectionAngle,
                directTaskRequest.getTargetInclinedDistance(),
                directTaskRequest.getTargetTiltAngle()
        );

        long deltaY = directCalculator.getDeltaY(
                targetDirectionAngle,
                directTaskRequest.getTargetInclinedDistance(),
                directTaskRequest.getTargetTiltAngle()
        );

        long deltaZ = directCalculator.getDeltaZ(
                directTaskRequest.getTargetInclinedDistance(),
                directTaskRequest.getTargetTiltAngle()
        );

        directTaskResponse.setTargetX(directTaskRequest.getBaseX() + deltaX);
        directTaskResponse.setTargetY(directTaskRequest.getBaseY() + deltaY);
        directTaskResponse.setTargetZ(directTaskRequest.getBaseZ() + deltaZ);

        return directTaskResponse;
    }
}

package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.DirectTaskDto;

/**
 * This class implements interface DirectTaskMapper
 */
@Component
@RequiredArgsConstructor
public class DirectTaskMapperDefault implements DirectTaskMapper{

    private final DirectCalculator directCalculator;
    private final Inverse inverseCalculator;

    @Override
    public DirectTaskDto toDirectTaskDto(DirectTaskDto directTaskDto) {

        long landmarkDirectionalAngle = inverseCalculator.getDirection(
                directTaskDto.getLandmarkX(),
                directTaskDto.getLandmarkY(),
                directTaskDto.getTargetX(),
                directTaskDto.getTargetY()
        );

        long targetDirectionAngle = directCalculator.getDirectionalAngle(
                landmarkDirectionalAngle,
                directTaskDto.getLandmarkDirection(),
                directTaskDto.getTargetDirection()
        );

        long deltaX = directCalculator.getDeltaX(
                targetDirectionAngle,
                directTaskDto.getTargetInclinedDistance(),
                directTaskDto.getTargetTiltAngle()
        );

        long deltaY = directCalculator.getDeltaY(
                targetDirectionAngle,
                directTaskDto.getTargetInclinedDistance(),
                directTaskDto.getTargetTiltAngle()
        );

        long deltaZ = directCalculator.getDeltaZ(
                directTaskDto.getTargetInclinedDistance(),
                directTaskDto.getTargetTiltAngle()
        );

        directTaskDto.setTargetX(directTaskDto.getBaseX() + deltaX);
        directTaskDto.setTargetY(directTaskDto.getBaseY() + deltaY);
        directTaskDto.setTargetZ(directTaskDto.getBaseZ() + deltaZ);

        return directTaskDto;
    }
}

package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;

@Component
@RequiredArgsConstructor
public class InverseTaskMapperDefault implements InverseTaskMapper{

    private final Inverse inverseCalculator;

    @Override
    public InverseTaskDto toInverseTaskDto(InverseTaskDto inverseTaskDto) {

        inverseTaskDto.setHorDistance(inverseCalculator.getHorDistance(inverseTaskDto.getBaseX(),
                inverseTaskDto.getBaseY(),
                inverseTaskDto.getTargetX(),
                inverseTaskDto.getTargetY()));
        inverseTaskDto.setInclinedDistance(inverseCalculator.getInclinedDistance(
                inverseTaskDto.getBaseX(),
                inverseTaskDto.getBaseY(),
                inverseTaskDto.getBaseZ(),
                inverseTaskDto.getTargetX(),
                inverseTaskDto.getTargetY(),
                inverseTaskDto.getTargetZ()));
        inverseTaskDto.setDirection(inverseCalculator.getDirection(
                inverseTaskDto.getBaseX(),
                inverseTaskDto.getBaseY(),
                inverseTaskDto.getTargetX(),
                inverseTaskDto.getTargetY()
        ));
        inverseTaskDto.setElevation(inverseCalculator.getElevation(
                inverseTaskDto.getBaseZ(),
                inverseTaskDto.getTargetZ()
        ));
        inverseTaskDto.setTiltAngle(inverseCalculator.getTiltAngle(
                inverseTaskDto.getBaseX(),
                inverseTaskDto.getBaseY(),
                inverseTaskDto.getBaseZ(),
                inverseTaskDto.getTargetX(),
                inverseTaskDto.getTargetY(),
                inverseTaskDto.getTargetZ()
        ));

        return inverseTaskDto;
    }

}

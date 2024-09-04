package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;

@Component
@RequiredArgsConstructor
public class InverseTaskMapperDefault implements InverseTaskMapper{

    private final Inverse inverseCalculator;

    @Override
    public InverseTaskResponse toInverseTaskResponse(InverseTaskRequest inverseTaskRequest) {

        InverseTaskResponse inverseTaskResponse = new InverseTaskResponse();

        inverseTaskResponse.setHorDistance(inverseCalculator.getHorDistance(inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY()));
        inverseTaskResponse.setInclinedDistance(inverseCalculator.getInclinedDistance(
                inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getBaseZ(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY(),
                inverseTaskRequest.getTargetZ()));
        inverseTaskResponse.setDirection(inverseCalculator.getDirection(
                inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY()
        ));
        inverseTaskResponse.setElevation(inverseCalculator.getElevation(
                inverseTaskRequest.getBaseZ(),
                inverseTaskRequest.getTargetZ()
        ));
        inverseTaskResponse.setTiltAngle(inverseCalculator.getTiltAngle(
                inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getBaseZ(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY(),
                inverseTaskRequest.getTargetZ()
        ));

        return inverseTaskResponse;
    }

}

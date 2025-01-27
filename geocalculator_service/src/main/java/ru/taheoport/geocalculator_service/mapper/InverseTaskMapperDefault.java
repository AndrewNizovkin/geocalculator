package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.*;

@Component
@RequiredArgsConstructor
public class InverseTaskMapperDefault implements InverseTaskMapper{

    private final InverseCalculator inverseCalculator;
    private final DataMapper dataMapper;

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

    @Override
    public InverseTaskFullResponse toInverseTaskFullResponse(InverseTaskRequest inverseTaskRequest) {
        InverseTaskFullResponse inverseTaskFullResponse = new InverseTaskFullResponse();

        inverseTaskFullResponse.setHorDistance(inverseCalculator.getHorDistance(inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY()));
        inverseTaskFullResponse.setInclinedDistance(inverseCalculator.getInclinedDistance(
                inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getBaseZ(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY(),
                inverseTaskRequest.getTargetZ()));
        inverseTaskFullResponse.setDirection(inverseCalculator.getDirection(
                inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY()
        ));
        inverseTaskFullResponse.setElevation(inverseCalculator.getElevation(
                inverseTaskRequest.getBaseZ(),
                inverseTaskRequest.getTargetZ()
        ));
        inverseTaskFullResponse.setTiltAngle(inverseCalculator.getTiltAngle(
                inverseTaskRequest.getBaseX(),
                inverseTaskRequest.getBaseY(),
                inverseTaskRequest.getBaseZ(),
                inverseTaskRequest.getTargetX(),
                inverseTaskRequest.getTargetY(),
                inverseTaskRequest.getTargetZ()
        ));

        inverseTaskFullResponse.setBaseX(inverseTaskRequest.getBaseX());
        inverseTaskFullResponse.setBaseY(inverseTaskRequest.getBaseY());
        inverseTaskFullResponse.setBaseZ(inverseTaskRequest.getBaseZ());
        inverseTaskFullResponse.setTargetX(inverseTaskRequest.getTargetX());
        inverseTaskFullResponse.setTargetY(inverseTaskRequest.getTargetY());
        inverseTaskFullResponse.setTargetZ(inverseTaskRequest.getTargetZ());

        return inverseTaskFullResponse;
    }

    /**
     * Converts the correct InverseStringRequest to InverseTaskRequest
     *
     * @param inverseStringRequest InverseStringRequest
     * @return InverseTaskRequest
     */
    @Override
    public InverseTaskRequest toInverseTaskRequest(InverseStringRequest inverseStringRequest) {
        InverseTaskRequest inverseTaskRequest = new InverseTaskRequest();
        inverseTaskRequest.setBaseX(dataMapper.meterToMillimeter(inverseStringRequest.getBaseX()));
        inverseTaskRequest.setBaseY(dataMapper.meterToMillimeter(inverseStringRequest.getBaseY()));
        inverseTaskRequest.setBaseZ(dataMapper.meterToMillimeter(inverseStringRequest.getBaseZ()));
        inverseTaskRequest.setTargetX(dataMapper.meterToMillimeter(inverseStringRequest.getTargetX()));
        inverseTaskRequest.setTargetY(dataMapper.meterToMillimeter(inverseStringRequest.getTargetY()));
        inverseTaskRequest.setTargetZ(dataMapper.meterToMillimeter(inverseStringRequest.getTargetZ()));

        return inverseTaskRequest;
    }

    /**
     * Converts the InverseTaskFullResponse to InverseStringResponse
     *
     * @param inverseTaskFullResponse InverseTaskFullResponse
     * @return InverseStringResponse
     */
    @Override
    public InverseStringResponse toInverseStringResponse(InverseTaskFullResponse inverseTaskFullResponse) {
        InverseStringResponse inverseStringResponse = new InverseStringResponse();
        inverseStringResponse.setBaseX(dataMapper.millimeterToMeter(inverseTaskFullResponse.getBaseX()));
        inverseStringResponse.setBaseY(dataMapper.millimeterToMeter(inverseTaskFullResponse.getBaseY()));
        inverseStringResponse.setBaseZ(dataMapper.millimeterToMeter(inverseTaskFullResponse.getBaseZ()));
        inverseStringResponse.setTargetX(dataMapper.millimeterToMeter(inverseTaskFullResponse.getTargetX()));
        inverseStringResponse.setTargetY(dataMapper.millimeterToMeter(inverseTaskFullResponse.getTargetY()));
        inverseStringResponse.setTargetZ(dataMapper.millimeterToMeter(inverseTaskFullResponse.getTargetZ()));
        inverseStringResponse.setDirection(dataMapper.secondsToDms(inverseTaskFullResponse.getDirection()));
        inverseStringResponse.setHorDistance(dataMapper.millimeterToMeter(inverseTaskFullResponse.getHorDistance()));
        inverseStringResponse.setInclinedDistance(dataMapper.millimeterToMeter(inverseTaskFullResponse.getInclinedDistance()));
        inverseStringResponse.setTiltAngle(dataMapper.secondsToDms(inverseTaskFullResponse.getTiltAngle()));
        inverseStringResponse.setElevation(dataMapper.millimeterToMeter(inverseTaskFullResponse.getElevation()));

        return inverseStringResponse;
    }

}

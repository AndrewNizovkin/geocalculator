package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.*;

/**
 * This class implements interface DirectTaskMapper
 */
@Component
@RequiredArgsConstructor
public class DirectTaskMapperDefault implements DirectTaskMapper{

    private final DirectCalculator directCalculator;
    private final InverseCalculator inverseCalculator;
    private final DataMapper dataMapper;

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
        directTaskResponse.setTargetZ(
                directTaskRequest.getBaseZ() +
                deltaZ +
                directTaskRequest.getBaseHeight() -
                directTaskRequest.getTargetHeight());

        return directTaskResponse;
    }

    @Override
    public DirectTaskFullResponse toDirectTaskFullResponse(DirectTaskRequest directTaskRequest) {
        DirectTaskFullResponse directTaskResponse = new DirectTaskFullResponse();

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
        directTaskResponse.setTargetZ(
                directTaskRequest.getBaseZ() +
                        deltaZ +
                        directTaskRequest.getBaseHeight() -
                        directTaskRequest.getTargetHeight());
        directTaskResponse.setLandmarkX(directTaskRequest.getLandmarkX());
        directTaskResponse.setLandmarkY(directTaskRequest.getLandmarkY());
        directTaskResponse.setLandmarkDirection(directTaskRequest.getLandmarkDirection());
        directTaskResponse.setBaseX(directTaskRequest.getBaseX());
        directTaskResponse.setBaseY(directTaskRequest.getBaseY());
        directTaskResponse.setBaseZ(directTaskRequest.getBaseZ());
        directTaskResponse.setBaseHeight(directTaskRequest.getBaseHeight());
        directTaskResponse.setTargetDirection(directTaskRequest.getTargetDirection());
        directTaskResponse.setTargetInclinedDistance(directTaskRequest.getTargetInclinedDistance());
        directTaskResponse.setTargetTiltAngle(directTaskRequest.getTargetTiltAngle());
        directTaskResponse.setTargetHeight(directTaskRequest.getTargetHeight());
        return directTaskResponse;
    }

    /**
     * Converts DirectStringRequest to DirectTaskRequest
     *
     * @param directStringRequest DirectStringRequest
     * @return DirectTaskRequest
     */
    @Override
    public DirectTaskRequest toDirectTaskRequest(DirectStringRequest directStringRequest) {
        DirectTaskRequest directTaskRequest = new DirectTaskRequest();

        directTaskRequest.setLandmarkX(dataMapper.meterToMillimeter(directStringRequest.getLandmarkX()));
        directTaskRequest.setLandmarkY(dataMapper.meterToMillimeter(directStringRequest.getLandmarkY()));
        directTaskRequest.setLandmarkDirection(dataMapper.dmsToSeconds(directStringRequest.getLandmarkDirection()));
        directTaskRequest.setBaseX(dataMapper.meterToMillimeter(directStringRequest.getBaseX()));
        directTaskRequest.setBaseY(dataMapper.meterToMillimeter(directStringRequest.getBaseY()));
        directTaskRequest.setBaseZ(dataMapper.meterToMillimeter(directStringRequest.getBaseZ()));
        directTaskRequest.setBaseHeight(dataMapper.meterToMillimeter(directStringRequest.getBaseHeight()));
        directTaskRequest.setTargetDirection(dataMapper.dmsToSeconds(directStringRequest.getTargetDirection()));
        directTaskRequest.setTargetInclinedDistance(dataMapper.meterToMillimeter(directStringRequest.getTargetInclinedDistance()));
        directTaskRequest.setTargetTiltAngle(dataMapper.dmsToSeconds(directStringRequest.getTargetTiltAngle()));
        directTaskRequest.setTargetHeight(dataMapper.meterToMillimeter(directStringRequest.getTargetHeight()));

        return directTaskRequest;
    }

    /**
     * Converts the DirectTaskFullResponse to DirectStringResponse
     * @param directTaskFullResponse DirectTaskFullResponse
     * @return DirectStringResponse
     */
    @Override
    public DirectStringResponse toDirectStringResponse(DirectTaskFullResponse directTaskFullResponse) {
        DirectStringResponse directStringResponse = new DirectStringResponse();

        directStringResponse.setTargetX(dataMapper.millimeterToMeter(directTaskFullResponse.getTargetX()));
        directStringResponse.setTargetY(dataMapper.millimeterToMeter(directTaskFullResponse.getTargetY()));
        directStringResponse.setTargetZ(dataMapper.millimeterToMeter(directTaskFullResponse.getTargetZ()));

        return directStringResponse;
    }

}

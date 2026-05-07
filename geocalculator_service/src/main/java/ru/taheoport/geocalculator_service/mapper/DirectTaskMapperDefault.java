package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.*;
import ru.taheoport.geocalculator_service.validator.DataValidator;

/**
 * This class implements interface DirectTaskMapper
 */
@Component
@RequiredArgsConstructor
public class DirectTaskMapperDefault implements DirectTaskMapper{

    private final DirectCalculator directCalculator;
    private final InverseCalculator inverseCalculator;
    private final DataMapper dataMapper;
    private final DataValidator dataValidator;

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

    /**
     * Gives response if raw data is not valid
     * @param message String message
     * @return DirectStringResponse
     */
    @Override
    public DirectStringResponse getDirectStringErrorResponse(String message) {
        DirectStringResponse directStringResponse = new DirectStringResponse();
        directStringResponse.setHeader(message);
        return directStringResponse;
    }

    /**
     * Checks the data in directStringRequest
     *
     * @param directStringRequest instance of DirectStringRequest with raw geodetic data
     * @return result of check
     */
    @Override
    public String checkDirectStringRequest(DirectStringRequest directStringRequest) {

        if (!dataValidator.isValidNumber(directStringRequest.getLandmarkX())) return "Invalid back X!";
        if (!dataValidator.isValidNumber(directStringRequest.getLandmarkY())) return "Invalid back Y!";
        if (!dataValidator.isValidHorizontalAngle(directStringRequest.getLandmarkDirection())) return "Invalid back direction!";
        if (!dataValidator.isValidNumber(directStringRequest.getBaseX())) return "Invalid base X!";
        if (!dataValidator.isValidNumber(directStringRequest.getBaseY())) return "Invalid base Y!";
        if (!dataValidator.isValidNumber(directStringRequest.getBaseZ())) return "Invalid base Z!";
        if (!dataValidator.isValidNumber(directStringRequest.getBaseHeight())) return "Invalid base height!";
        if (!dataValidator.isValidHorizontalAngle(directStringRequest.getTargetDirection())) return "Invalid target direction!";
        if (!dataValidator.isValidPositiveNumber(directStringRequest.getTargetInclinedDistance())) return "Invalid distance!";
        if (!dataValidator.isValidTiltAngle(directStringRequest.getTargetTiltAngle())) return "Invalid tilt angle!";
        if (!dataValidator.isValidNumber(directStringRequest.getTargetHeight())) return "Invalid target height!";

        return "OK";
    }

}

package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

@Component
@RequiredArgsConstructor
public class PotenotTaskMapperImpl implements PotenotTaskMapper{

    private final PotenotCalculator potenotCalculator;
    private final DataMapper dataMapper;

    @Override
    public PotenotTaskResponse solvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList) {

        double firstX = (double) potenotTaskRequestList.get(0).getPointX();
        double firstY = (double) potenotTaskRequestList.get(0).getPointY();
        double secondX = (double) potenotTaskRequestList.get(1).getPointX();
        double secondY = (double) potenotTaskRequestList.get(1).getPointY();
        double thirdX = (double) potenotTaskRequestList.get(2).getPointX();
        double thirdY = (double) potenotTaskRequestList.get(2).getPointY();

        double firstAngle = toRadians((double) potenotCalculator.difAngle(potenotTaskRequestList.get(0).getDirection(),
                potenotTaskRequestList.get(1).getDirection()) / 3600);
        double secondAngle = toRadians((double) potenotCalculator.difAngle(potenotTaskRequestList.get(0).getDirection(),
                potenotTaskRequestList.get(2).getDirection()) / 3600);

        double dirFromFirst = potenotCalculator.dirFromFirst(
                firstX,
                firstY,
                secondX,
                secondY,
                thirdX,
                thirdY,
                firstAngle,
                secondAngle
        );

        double dirFromSecond = potenotCalculator.addAngle(dirFromFirst, firstAngle);
        double dirFromThird = potenotCalculator.addAngle(dirFromFirst, secondAngle);

        double targetX = potenotCalculator.targetX(
                firstX,
                firstY,
                secondX,
                secondY,
                dirFromFirst,
                dirFromSecond
        );

        double targetY = potenotCalculator.targetY(
                firstY,
                targetX,
                firstX,
                dirFromFirst
        );


        PotenotTaskResponse potenotTaskResponse = new PotenotTaskResponse();

        potenotTaskResponse.setPointX(round(targetX));
        potenotTaskResponse.setPointY(round(targetY));

        return potenotTaskResponse;
    }

    /**
     * Converts instance of PotenotStringRequest to instance of PotenotTaskRequest
     * @param potenotStringRequest instance of PotenotStringRequest
     * @return instanse of PotenotTaskRequest
     */
    @Override
    public PotenotTaskRequest toPotenotTaskRequest(PotenotStringRequest potenotStringRequest) {
        PotenotTaskRequest potenotTaskRequest = new PotenotTaskRequest();
        potenotTaskRequest.setPointX(dataMapper.meterToMillimeter(potenotStringRequest.getPointX()));
        potenotTaskRequest.setPointY(dataMapper.meterToMillimeter(potenotStringRequest.getPointY()));
        potenotTaskRequest.setDirection(dataMapper.dmsToSeconds(potenotStringRequest.getDirection()));
        return potenotTaskRequest;
    }

    /**
     * Converts list of PotenotStringRequest to list of PotenotTaskRequest
     * @param potenotStringRequests list of PotenotStringRequest
     * @return list of PotenotTaskRequest
     */
    @Override
    public List<PotenotTaskRequest> toPotenotTaskRequests(List<PotenotStringRequest> potenotStringRequests) {
        return potenotStringRequests.stream().map(this::toPotenotTaskRequest).toList();
    }


    /**
     * Converts PotenotTaskResponse to PotenotStringResponse
     * @param potenotTaskResponse contains target coordinates in millimeters
     * @return PotenotStringResponse with target coordinates in meters
     */
    @Override
    public PotenotStringResponse toPotenotStringResponse(PotenotTaskResponse potenotTaskResponse) {
        PotenotStringResponse potenotStringResponse = new PotenotStringResponse();
        potenotStringResponse.setPointX(dataMapper.millimeterToMeter(potenotTaskResponse.getPointX()));
        potenotStringResponse.setPointY(dataMapper.millimeterToMeter(potenotTaskResponse.getPointY()));
        return potenotStringResponse;
    }
}

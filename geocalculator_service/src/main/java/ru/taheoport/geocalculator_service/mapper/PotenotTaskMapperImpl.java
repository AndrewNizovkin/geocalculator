package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;

import java.util.List;
import static java.lang.Math.*;

@Component
@RequiredArgsConstructor
public class PotenotTaskMapperImpl implements PotenotTaskMapper{

    private final PotenotCalculator potenotCalculator;

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

        double targetX1 = potenotCalculator.targetX(
                firstX,
                firstY,
                secondX,
                secondY,
                dirFromFirst,
                dirFromSecond
        );

        double targetY1 = potenotCalculator.targetY(
                firstY,
                targetX1,
                firstX,
                dirFromFirst
        );

        double targetX2 = potenotCalculator.targetX(
                secondX,
                secondY,
                thirdX,
                thirdY,
                dirFromSecond,
                dirFromThird
        );

        double targetY2 = potenotCalculator.targetY(
                secondY,
                targetX2,
                secondX,
                dirFromSecond
        );

        PotenotTaskResponse potenotTaskResponse = new PotenotTaskResponse();

        potenotTaskResponse.setPointX(round(targetX1));
        potenotTaskResponse.setPointY(round(targetY1));
//        potenotTaskResponse.setPointX(round((targetX1 + targetX2) / 2));
//        potenotTaskResponse.setPointY(round((targetY1 + targetY2) / 2));
//        potenotTaskResponse.setDeltaX(round(targetX1 - targetX2));
//        potenotTaskResponse.setDeltaY(round(targetY1 - targetY2));

        return potenotTaskResponse;
    }
}

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

        double dirFromFirst = atan(
                ((secondY - firstY) * 1 / tan(firstAngle) +
                        (firstY - thirdY) * 1 /tan(secondAngle) -
                        secondX + thirdX) /
                        ((secondX - firstX) * 1 / tan(firstAngle) +
                                (firstX - thirdX) * 1 / tan(secondAngle) +
                                secondY - thirdY)
        );

        double dirFromSecond = dirFromFirst + firstAngle;
        double dirFromThird = dirFromFirst + secondAngle;

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



        return null;
    }
}
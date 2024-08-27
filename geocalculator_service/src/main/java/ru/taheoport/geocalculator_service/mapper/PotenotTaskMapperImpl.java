package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotTaskDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PotenotTaskMapperImpl implements PotenotTaskMapper{

    private final PotenotCalculator potenotCalculator;

    @Override
    public PotenotTaskDto solvePotenotTask(List<PotenotTaskDto> potenotTaskDtoList) {

        double firstX = (double) potenotTaskDtoList.get(0).getPointX();
        double firstY = (double) potenotTaskDtoList.get(0).getPointY();
        double secondX = (double) potenotTaskDtoList.get(1).getPointX();
        double secondY = (double) potenotTaskDtoList.get(1).getPointY();
        double thirdX = (double) potenotTaskDtoList.get(2).getPointX();
        double thirdY = (double) potenotTaskDtoList.get(2).getPointY();
        double firstAngle = potenotCalculator.difAngle(potenotTaskDtoList.get(0).getDirection(),
                potenotTaskDtoList.get(1).getDirection());
        double secondAngle = potenotCalculator.difAngle(potenotTaskDtoList.get(0).getDirection(),
                potenotTaskDtoList.get(2).getDirection());

        return null;
    }
}

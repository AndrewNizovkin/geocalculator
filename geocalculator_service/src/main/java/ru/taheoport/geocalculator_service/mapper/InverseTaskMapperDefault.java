package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.model.InverseTask;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InverseTaskMapperDefault implements InverseTaskMapper{

    private final InverseTaskCalculator inverseTaskCalculator;

    @Override
    public InverseTaskDto toInverseTaskDto(InverseTask inverseTask) {
        InverseTaskDto inverseTaskDto = new InverseTaskDto();
        inverseTaskDto.setId(inverseTask.getId());
        inverseTaskDto.setBaseX(inverseTask.getBaseX());
        inverseTaskDto.setBaseY(inverseTask.getBaseY());
        inverseTaskDto.setBaseZ(inverseTask.getBaseZ());
        inverseTaskDto.setTargetX(inverseTask.getTargetX());
        inverseTaskDto.setTargetY(inverseTask.getTargetY());
        inverseTaskDto.setTargetZ(inverseTask.getTargetZ());
        inverseTaskDto.setHorDistance(inverseTaskCalculator.getHorDistance(inverseTask));
        inverseTaskDto.setInclinedDistance(inverseTaskCalculator.getInclinedDistance(inverseTask));
        inverseTaskDto.setDirection(inverseTaskCalculator.getDirection(inverseTask));
        inverseTaskDto.setElevation(inverseTaskCalculator.getElevation(inverseTask));
        inverseTaskDto.setTiltAngle(inverseTaskCalculator.getTiltAngle(inverseTask));



        return inverseTaskDto;
    }

    @Override
    public InverseTask toInverseTask(InverseTaskDto inverseTaskDto) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setBaseX(inverseTaskDto.getBaseX());
        inverseTask.setBaseY(inverseTaskDto.getBaseY());
        inverseTask.setBaseZ(inverseTaskDto.getBaseZ());
        inverseTask.setTargetX(inverseTaskDto.getTargetX());
        inverseTask.setTargetY(inverseTaskDto.getTargetY());
        inverseTask.setTargetZ(inverseTaskDto.getTargetZ());

        return inverseTask;
    }

    @Override
    public List<InverseTaskDto> toListInverseTaskDto(List<InverseTask> inverseTasks) {
        return inverseTasks.stream().map(this::toInverseTaskDto).toList();
    }
}

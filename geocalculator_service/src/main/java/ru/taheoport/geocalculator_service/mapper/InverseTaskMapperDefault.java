package ru.taheoport.geocalculator_service.mapper;

import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.model.InverseTask;

import java.util.List;

@Component
public class InverseTaskMapperDefault implements InverseTaskMapper{

    @Override
    public InverseTaskDto toInverseTaskDto(InverseTask inverseTask) {
        InverseTaskDto inverseTaskDto = new InverseTaskDto();
        inverseTaskDto.setId(inverseTask.getId());
        inverseTaskDto.setFirstX(inverseTask.getFirstX());
        inverseTaskDto.setFirstY(inverseTask.getFirstY());
        inverseTaskDto.setFirstZ(inverseTask.getFirstZ());
        inverseTaskDto.setSecondX(inverseTask.getSecondX());
        inverseTaskDto.setSecondY(inverseTask.getSecondY());
        inverseTaskDto.setSecondZ(inverseTask.getSecondZ());

        return inverseTaskDto;
    }

    @Override
    public InverseTask toInverseTask(InverseTaskDto inverseTaskDto) {
        InverseTask inverseTask = new InverseTask();
        inverseTask.setFirstX(inverseTaskDto.getFirstX());
        inverseTask.setFirstY(inverseTaskDto.getFirstY());
        inverseTask.setFirstZ(inverseTaskDto.getFirstZ());
        inverseTask.setSecondX(inverseTaskDto.getSecondX());
        inverseTask.setSecondY(inverseTaskDto.getSecondY());
        inverseTask.setSecondZ(inverseTaskDto.getSecondZ());

        return inverseTask;
    }

    @Override
    public List<InverseTaskDto> toListInverseTaskDto(List<InverseTask> inverseTasks) {
        return inverseTasks.stream().map(this::toInverseTaskDto).toList();
    }
}

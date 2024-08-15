package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.model.GeoPoint;
import ru.taheoport.geocalculator_service.model.InverseTask;
import ru.taheoport.geocalculator_service.repository.GeoPointRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InverseTaskMapperDefault implements InverseTaskMapper{

    private final InverseTaskCalculator inverseTaskCalculator;
    private final GeoPointRepository geoPointRepository;

    @Override
    public InverseTaskDto toInverseTaskDto(InverseTask inverseTask) {
        GeoPoint target = geoPointRepository.findById(inverseTask.getTargetId()).orElse(null);
        GeoPoint base = geoPointRepository.findById(inverseTask.getBaseId()).orElse(null);

        if (target == null || base == null) throw new RuntimeException("Not found element");

        InverseTaskDto inverseTaskDto = new InverseTaskDto();

        inverseTaskDto.setId(inverseTask.getId());
        inverseTaskDto.setBaseX(base.getXPoint());
        inverseTaskDto.setBaseY(base.getYPoint());
        inverseTaskDto.setBaseZ(base.getZPoint());
        inverseTaskDto.setTargetX(target.getXPoint());
        inverseTaskDto.setTargetY(target.getYPoint());
        inverseTaskDto.setTargetZ(target.getZPoint());
        inverseTaskDto.setHorDistance(inverseTaskCalculator.getHorDistance(inverseTask));
        inverseTaskDto.setInclinedDistance(inverseTaskCalculator.getInclinedDistance(inverseTask));
        inverseTaskDto.setDirection(inverseTaskCalculator.getDirection(inverseTask));
        inverseTaskDto.setElevation(inverseTaskCalculator.getElevation(inverseTask));
        inverseTaskDto.setTiltAngle(inverseTaskCalculator.getTiltAngle(inverseTask));



        return inverseTaskDto;
    }

    @Override
    public InverseTask toInverseTask(InverseTaskDto inverseTaskDto) {
        
        GeoPoint base = geoPointRepository.saveAndFlush(new GeoPoint(
                inverseTaskDto.getBaseX(), 
                inverseTaskDto.getBaseY(), 
                inverseTaskDto.getBaseZ()
        ));
        GeoPoint target = geoPointRepository.saveAndFlush(new GeoPoint(
                inverseTaskDto.getTargetX(), 
                inverseTaskDto.getTargetY(), 
                inverseTaskDto.getTargetZ()
        ));
        return new InverseTask(
                base.getId(),
                target.getId()
        );

//        inverseTask.setBaseId(base.getId());
//        inverseTask.setTargetId(target.getId());
        
//        inverseTask.setBaseX(inverseTaskDto.getBaseX());
//        inverseTask.setBaseY(inverseTaskDto.getBaseY());
//        inverseTask.setBaseZ(inverseTaskDto.getBaseZ());
//        inverseTask.setTargetX(inverseTaskDto.getTargetX());
//        inverseTask.setTargetY(inverseTaskDto.getTargetY());
//        inverseTask.setTargetZ(inverseTaskDto.getTargetZ());

//        return inverseTask;
    }

    @Override
    public List<InverseTaskDto> toListInverseTaskDto(List<InverseTask> inverseTasks) {
        return inverseTasks.stream().map(this::toInverseTaskDto).toList();
    }
}

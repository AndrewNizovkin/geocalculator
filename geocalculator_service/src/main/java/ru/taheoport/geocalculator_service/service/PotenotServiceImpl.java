package ru.taheoport.geocalculator_service.service;

import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotTaskDto;

import java.util.List;

/**
 * This class implements interface PotenotService
 */
@Component
public class PotenotServiceImpl implements PotenotService{
    @Override
    public PotenotTaskDto resolvePotenotTask(List<PotenotTaskDto> potenotTaskDtoList) {
        if (potenotTaskDtoList.size() != 3) {
            throw new RuntimeException("There is not enough data to calculate");
        } else {

            return potenotTaskDtoList.getFirst();

        }
    }
}

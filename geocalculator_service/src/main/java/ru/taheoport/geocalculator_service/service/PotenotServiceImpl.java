package ru.taheoport.geocalculator_service.service;

import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;

import java.util.List;

/**
 * This class implements interface PotenotService
 */
@Component
public class PotenotServiceImpl implements PotenotService{
    @Override
    public PotenotTaskRequest resolvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList) {
        if (potenotTaskRequestList.size() != 3) {
            throw new RuntimeException("There is not enough data to calculate");
        } else {

            return potenotTaskRequestList.getFirst();

        }
    }
}

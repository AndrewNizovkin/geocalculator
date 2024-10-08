package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.mapper.PotenotTaskMapper;

import java.util.List;

/**
 * This class implements interface PotenotService
 */
@Component
@RequiredArgsConstructor
public class PotenotServiceImpl implements PotenotService{

    private final PotenotTaskMapper potenotTaskMapper;

    @Override
    public PotenotTaskResponse resolvePotenotTask(List<PotenotTaskRequest> potenotTaskRequestList) {
        if (potenotTaskRequestList.size() != 3) {
            throw new RuntimeException("There is not enough data to calculate");
        } else {

            return potenotTaskMapper.solvePotenotTask(potenotTaskRequestList);

        }
    }
}

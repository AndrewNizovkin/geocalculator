package ru.taheoport.geocalculator_web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_web.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_web.dto.InverseTaskResponse;
import ru.taheoport.geocalculator_web.provider.InverseTaskProvider;

@Service
@RequiredArgsConstructor
public class InverseTaskServiseDefault implements InverseTaskService{

    private final InverseTaskProvider inverseTaskProvider;

    /**
     * Solves inverse geodetic task
     * @param inverseTaskRequest InverseTaskRequest
     * @return InverseTaskResponse
     */
    @Override
    public InverseTaskResponse solveInverseTask(InverseTaskRequest inverseTaskRequest) {
        return inverseTaskProvider.getInverseTaskResponse(inverseTaskRequest);
    }
}

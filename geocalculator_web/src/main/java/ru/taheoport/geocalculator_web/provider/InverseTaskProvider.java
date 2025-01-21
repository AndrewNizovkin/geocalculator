package ru.taheoport.geocalculator_web.provider;

import ru.taheoport.geocalculator_web.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_web.dto.InverseTaskResponse;

public interface InverseTaskProvider {

    /**
     * Gets inverseTaskResponse from geocalculator_service
     * @param inverseTaskRequest InverseTaskRequest
     * @return InverseTaskResponse
     */
    InverseTaskResponse getInverseTaskResponse(InverseTaskRequest inverseTaskRequest);
}

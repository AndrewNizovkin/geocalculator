package ru.taheoport.geocalculator_web.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.taheoport.geocalculator_web.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_web.dto.InverseTaskResponse;

@Component
public class InverseTaskProviderDefault implements InverseTaskProvider{

    private final WebClient webClient;

    public InverseTaskProviderDefault(WebClient webClient) {
        this.webClient = webClient;
    }


    /**
     * Gets inverseTaskResponse from geocalculator_service
     * @param inverseTaskRequest InverseTaskRequest
     * @return InverseTaskResponse
     */
    @Override
    public InverseTaskResponse getInverseTaskResponse(InverseTaskRequest inverseTaskRequest) {
        return null;
    }
}

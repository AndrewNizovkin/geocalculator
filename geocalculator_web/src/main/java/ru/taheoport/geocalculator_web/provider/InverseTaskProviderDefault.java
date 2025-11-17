package ru.taheoport.geocalculator_web.provider;

import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
import ru.taheoport.geocalculator_web.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_web.dto.InverseTaskResponse;

@Component
public class InverseTaskProviderDefault implements InverseTaskProvider{

//    private final WebClient webClient;

//    public InverseTaskProviderDefault(WebClient webClient) {
//        this.webClient = webClient;
//    }


    /**
     * Gets inverseTaskResponse from geocalculator_service
     * @param inverseTaskRequest InverseTaskRequest
     * @return InverseTaskResponse
     */
    @Override
    public InverseTaskResponse getInverseTaskResponse(InverseTaskRequest inverseTaskRequest) {
        InverseTaskResponse inverseTaskResponse = new InverseTaskResponse();

        inverseTaskResponse.setBaseX(inverseTaskRequest.getBaseX());
        inverseTaskResponse.setBaseY(inverseTaskRequest.getBaseY());
        inverseTaskResponse.setBaseZ(inverseTaskRequest.getBaseZ());
        inverseTaskResponse.setTargetX(inverseTaskRequest.getTargetX());
        inverseTaskResponse.setTargetY(inverseTaskRequest.getTargetY());
        inverseTaskResponse.setTargetZ(inverseTaskRequest.getTargetZ());
        inverseTaskResponse.setDirection(201);
        inverseTaskResponse.setHorDistance(202);
        inverseTaskResponse.setInclinedDistance(203);
        inverseTaskResponse.setTiltAngle(204);
        inverseTaskResponse.setElevation(-205);

        return inverseTaskResponse;
    }
}

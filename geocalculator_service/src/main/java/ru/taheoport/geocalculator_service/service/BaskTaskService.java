package ru.taheoport.geocalculator_service.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.model.BackTaskRequest;
import static java.lang.Math.*;

@Service
public class BaskTaskService implements BackTask{
    @Override
    public long getHorDistance(BackTaskRequest backTaskRequest) {
        double result;
        result = sqrt(pow((double) (backTaskRequest.getFirstX() - backTaskRequest.getSecondX()), 2) +
                pow((double) (backTaskRequest.getFirstY() - backTaskRequest.getSecondY()), 2));

        return round(result);
    }

    @Override
    public long getInclinedDistance(BackTaskRequest backTaskRequest) {
        double result;
        result = sqrt(pow((double) (backTaskRequest.getFirstX() - backTaskRequest.getSecondX()), 2) +
                pow((double) (backTaskRequest.getFirstY() - backTaskRequest.getSecondY()), 2) +
                pow((double) backTaskRequest.getFirstZ() - backTaskRequest.getSecondZ(), 2));
        return round(result);
    }

    @Override
    public double getDirection(BackTaskRequest backTaskRequest) {
        return 0;
    }
}

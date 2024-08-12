package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.BackTaskRequest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {BaskTaskService.class})
class BaskTaskServiceTest {

    @Autowired
    private BackTask backTask;


    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 1000000, 2000000000, 2000000000, 11000, 1414213562",
            "-1000000000, -1000000000, -1000000, -2000000000, -2000000000, -1100000, 1414213562"
    })
    void getHorDistance(long firstX,
                        long firstY,
                        long firstZ,
                        long secondX,
                        long secondY,
                        long secondZ,
                        long expect) {
        BackTaskRequest backTaskRequest = new BackTaskRequest();
        backTaskRequest.setFirstX(firstX);
        backTaskRequest.setFirstY(firstY);
        backTaskRequest.setFirstZ(firstZ);
        backTaskRequest.setSecondX(secondX);
        backTaskRequest.setSecondY(secondY);
        backTaskRequest.setSecondZ(secondZ);

        long actualResult = backTask.getHorDistance(backTaskRequest);

        assertEquals(expect, actualResult);


    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 1000000000, 1000000, 2000000000, 2000000000, 1100000, 1414213566",
            "-1000000000, -1000000000, -1000000, -2000000000, -2000000000, -1100000, 1414213566"
    })
    void getInclinedDistance(long firstX,
                        long firstY,
                        long firstZ,
                        long secondX,
                        long secondY,
                        long secondZ,
                        long expect) {
        BackTaskRequest backTaskRequest = new BackTaskRequest();
        backTaskRequest.setFirstX(firstX);
        backTaskRequest.setFirstY(firstY);
        backTaskRequest.setFirstZ(firstZ);
        backTaskRequest.setSecondX(secondX);
        backTaskRequest.setSecondY(secondY);
        backTaskRequest.setSecondZ(secondZ);

        long actualResult = backTask.getInclinedDistance(backTaskRequest);

        assertEquals(expect, actualResult);


    }

    @Test
    void getDirection() {
    }
}
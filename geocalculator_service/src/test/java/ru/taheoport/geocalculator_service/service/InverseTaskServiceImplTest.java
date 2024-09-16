package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;
import ru.taheoport.geocalculator_service.mapper.InverseTaskMapper;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InverseTaskServiceImpl.class})
class InverseTaskServiceImplTest {

    @Autowired
    InverseTaskService inverseTaskService;

    @MockBean
    InverseTaskMapper inverseTaskMapper;

    @Test
    void solveInverseTaskTest() {

        InverseTaskRequest inverseTaskRequest = new InverseTaskRequest();
        InverseTaskResponse expect = new InverseTaskResponse();
        expect.setDirection(324000);
        expect.setHorDistance(456);
        expect.setInclinedDistance(567);
        expect.setTiltAngle(23409);
        expect.setElevation(100);
        Mockito.when(inverseTaskMapper.toInverseTaskResponse(inverseTaskRequest)).thenReturn(expect);

        InverseTaskResponse actual = inverseTaskService.solveInverseTask(inverseTaskRequest);

        assertNotNull(actual);
        assertEquals(expect.getDirection(), actual.getDirection());
        assertEquals(expect.getHorDistance(), actual.getHorDistance());
        assertEquals(expect.getInclinedDistance(), actual.getInclinedDistance());
        assertEquals(expect.getTiltAngle(), actual.getTiltAngle());
        assertEquals(expect.getElevation(), actual.getElevation());
    }
}
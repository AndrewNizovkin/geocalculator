package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import ru.taheoport.geocalculator_service.mapper.DirectTaskMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DirectTaskServiceImpl.class})
class DirectTaskServiceImplTest {

    @Autowired
    DirectTaskService directTaskService;

    @MockBean
    DirectTaskMapper directTaskMapper;

    @Test
    void solveDirectTask() {

        DirectTaskRequest directTaskRequest = new DirectTaskRequest();
        DirectTaskResponse expect = new DirectTaskResponse();
        expect.setTargetX(10000000);
        expect.setTargetY(200000000);
        expect.setTargetZ(100000000);
        Mockito.when(directTaskMapper.toDirectTaskResponse(directTaskRequest)).thenReturn(expect);

        DirectTaskResponse actual = directTaskService.solveDirectTask(directTaskRequest);

        assertNotNull(actual);
        assertEquals(expect.getTargetX(), actual.getTargetX());
        assertEquals(expect.getTargetY(), actual.getTargetY());
        assertEquals(expect.getTargetZ(), actual.getTargetZ());

    }
}
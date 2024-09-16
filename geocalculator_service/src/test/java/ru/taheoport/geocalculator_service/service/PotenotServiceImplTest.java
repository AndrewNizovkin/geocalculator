package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.mapper.PotenotTaskMapperImpl;
import org.mockito.Mockito;

import java.util.List;

@SpringBootTest(classes = {PotenotServiceImpl.class})
class PotenotServiceImplTest {

    @Autowired
    PotenotService potenotService;

    @MockBean
    PotenotTaskMapperImpl potenotTaskMapper;

    @Test
    void resolvePotenotTaskTest() {

        List<PotenotTaskRequest> potenotTaskRequestList = List.of(
                new PotenotTaskRequest(10, 10, 10),
                new PotenotTaskRequest(20, 20, 20),
                new PotenotTaskRequest(30, 30, 30)
        );
        PotenotTaskResponse expect = new PotenotTaskResponse(50, 50);
        Mockito.when(potenotTaskMapper.solvePotenotTask(potenotTaskRequestList)).thenReturn(expect);

        PotenotTaskResponse actual = potenotService.resolvePotenotTask(potenotTaskRequestList);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expect.getPointX(), actual.getPointX());
        Assertions.assertEquals(expect.getPointY(), actual.getPointY());
    }
}
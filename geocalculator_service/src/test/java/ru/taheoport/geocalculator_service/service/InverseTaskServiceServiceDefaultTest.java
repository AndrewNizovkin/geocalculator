package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.taheoport.geocalculator_service.model.InverseTask;
import ru.taheoport.geocalculator_service.repository.InverseTaskRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {InverseTaskServiceServiceDefault.class})
class InverseTaskServiceServiceDefaultTest {

    @Autowired
    private InverseTaskService inverseTaskService;

    @MockBean
    private InverseTaskRepository inverseTaskRepository;



}
package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

@RestController
@RequestMapping("inverse")
@RequiredArgsConstructor
public class InverseTackController {

    private final InverseTaskService inverseTaskService;


    /**
     * Resolves Inverse Geodetic Problem
     * @param inverseTaskRequest Instance of InverseTaskDto
     * @return Instance InverseTaskDto
     */
    @PostMapping
    public ResponseEntity<InverseTaskResponse> solveInverseTask(@RequestBody InverseTaskRequest inverseTaskRequest) {
        return new ResponseEntity<>(inverseTaskService.solveInverseTask(inverseTaskRequest), HttpStatus.CREATED);
    }
}

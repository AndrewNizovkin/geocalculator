package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

@RestController
@RequestMapping("inverse")
@RequiredArgsConstructor
public class InverseTackController {

    private final InverseTaskService inverseTaskService;


    /**
     * Resolves Inverse Geodetic Problem
     * @param inverseTaskDto Instance of InverseTaskDto
     * @return Instance InverseTaskDto
     */
    @PostMapping
    public ResponseEntity<InverseTaskDto> solveInverseTask(@RequestBody InverseTaskDto inverseTaskDto) {
        return new ResponseEntity<>(inverseTaskService.solveInverseTask(inverseTaskDto), HttpStatus.CREATED);
    }
}

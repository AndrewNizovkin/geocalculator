package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taheoport.geocalculator_service.dto.DirectTaskRequest;
import ru.taheoport.geocalculator_service.dto.DirectTaskResponse;
import ru.taheoport.geocalculator_service.service.DirectTaskService;

@RestController
@RequestMapping("direct")
@RequiredArgsConstructor
public class DirectTaskController {

    private final DirectTaskService directTaskService;

    @PostMapping
    public ResponseEntity<DirectTaskResponse> solveDirectTask(@RequestBody DirectTaskRequest directTaskRequest) {
        return new ResponseEntity<>(directTaskService.solveDirectTask(directTaskRequest), HttpStatus.CREATED);
    }
}

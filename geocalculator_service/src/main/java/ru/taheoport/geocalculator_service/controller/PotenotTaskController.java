package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.service.PotenotService;

import java.util.List;

@RestController
@RequestMapping("potenot")
@RequiredArgsConstructor
public class PotenotTaskController {

    private final PotenotService potenotService;

    @PostMapping
    public ResponseEntity<PotenotTaskResponse> solvePotenotProblem(@RequestBody List<PotenotTaskRequest> potenotTaskRequestList) {
        try {
            return ResponseEntity.ok().body(potenotService.resolvePotenotTask(potenotTaskRequestList));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}

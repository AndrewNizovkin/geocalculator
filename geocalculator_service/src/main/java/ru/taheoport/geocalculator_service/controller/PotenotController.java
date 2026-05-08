package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.dto.PotenotStringResponse;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.service.PotenotService;

import java.util.List;

@RestController
@RequestMapping("potenot")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PotenotController {

    private final PotenotService potenotService;

    @PostMapping
    public ResponseEntity<PotenotTaskResponse> solvePotenotTask(@RequestBody List<PotenotTaskRequest> potenotTaskRequestList) {

        try {
            return new ResponseEntity<>(potenotService.resolvePotenotTask(potenotTaskRequestList), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Resolves Potenot geodesic task
     * @param potenotStringRequests List<PotenotStringRequest>
     * @return PotenotStringRequest
     */
    @PostMapping("str")
    public ResponseEntity<PotenotStringResponse> getPotenotStringResponse(@RequestBody List<PotenotStringRequest> potenotStringRequests) {
        return new ResponseEntity<>(potenotService.getPotenotStringResponse(potenotStringRequests), HttpStatus.CREATED);
    }



}

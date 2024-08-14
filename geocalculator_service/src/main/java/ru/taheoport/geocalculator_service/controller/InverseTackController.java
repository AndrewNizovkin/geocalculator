package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.taheoport.geocalculator_service.dto.InverseTaskDto;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

import java.util.List;

@RestController
@RequestMapping("inverse")
@RequiredArgsConstructor
public class InverseTackController {

    private final InverseTaskService inverseTaskService;

    /**
     * Gets all tasks from db
     * @return List of instance InverseTaskDto
     */
    @GetMapping
    public ResponseEntity<List<InverseTaskDto>> findAll() {
        return ResponseEntity.ok().body(inverseTaskService.findAll());
    }

    /**
     * Gets iverse task by id
     * @param id long id
     * @return Instance of InverseTaskDto
     */
    @GetMapping("{id}")
    public ResponseEntity<InverseTaskDto> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(inverseTaskService.findById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Creates new InverseTask
     * @param inverseTaskDto Instance of InverseTaskDto
     * @return Instance InverseTaskDto
     */
    @PostMapping
    public ResponseEntity<InverseTaskDto> createInverseTask(@RequestBody InverseTaskDto inverseTaskDto) {
        return new ResponseEntity<>(inverseTaskService.createInverseTask(inverseTaskDto), HttpStatus.CREATED);
    }
}

package ru.taheoport.geocalculator_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Reverse geodetic serif", description =
            "Обратная геодезическая засечка или задача Потенота определяет " +
                    "координаты точки target по координатам трёх или более точек с " +
                    "известными координатами и угловыми направлениями с target на каждую из них")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<PotenotTaskResponse> solvePotenotProblem(@RequestBody List<PotenotTaskRequest> potenotTaskRequestList) {
        try {
            return new ResponseEntity<>(potenotService.resolvePotenotTask(potenotTaskRequestList), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}

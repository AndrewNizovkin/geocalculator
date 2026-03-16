package ru.taheoport.geocalculator_service.controller;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;
import ru.taheoport.geocalculator_service.dto.InverseStringResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.service.InverseTaskService;
import ru.taheoport.geocalculator_service.validator.InverseValidator;

@RestController
@RequestMapping("inverse")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InverseController {

    private final InverseTaskService inverseTaskService;
    private final InverseValidator inverseValidator;


    /**
     * Resolves Inverse Geodetic Problem
     * @param inverseTaskRequest Instance of InverseTaskDto
     * @return Instance InverseTaskDto
     */
//    @Operation(summary = "Resolves Inverse Geodetic Problem", description =
//            "По известным трёхмерным координатам двух пунктов (base и target) " +
//                    "определяет горизонтальное и наклонное расстояние между ними, " +
//                    "дирекционный угол направления base->target, " +
//                    "угол наклона линии base->target, " +
//                    "превышение между пунктами")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Created"),
//            @ApiResponse(responseCode = "400", description = "Bad request"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
    @PostMapping
    public ResponseEntity<InverseTaskFullResponse> solveInverseTask(@RequestBody InverseTaskRequest inverseTaskRequest) {
        return new ResponseEntity<>(inverseTaskService.getInverseTaskFullResponse(inverseTaskRequest), HttpStatus.CREATED);
    }

    /**
     * Resolves inverse geodetic task
     * @param inverseStringRequest InverseStringRequest
     * @return InverseStringRequest
     */
    @PostMapping("str")
    public ResponseEntity<InverseStringResponse> getInverseStringResponse(@RequestBody InverseStringRequest inverseStringRequest) {
        if (inverseValidator.isValidInverseStringRequest(inverseStringRequest)) {
            return new ResponseEntity<>(inverseTaskService.getInverseStringResponse(inverseStringRequest), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(inverseTaskService.getInverseStringErrorResponse(), HttpStatus.CREATED);
        }

    }
}

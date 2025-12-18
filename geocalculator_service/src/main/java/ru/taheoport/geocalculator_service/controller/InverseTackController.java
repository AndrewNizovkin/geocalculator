package ru.taheoport.geocalculator_service.controller;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.dto.InverseTaskFullResponse;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskResponse;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

@RestController
@RequestMapping("inverse")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InverseTackController {

    private final InverseTaskService inverseTaskService;


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
}

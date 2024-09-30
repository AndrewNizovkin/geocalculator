package ru.taheoport.geocalculator_service.controller;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

//    @Operation(summary = "Resolves Direct Geodetic Problem", description = "Определяет координаты точки (target) по известным координатам двух точек (base и landmark), расстоянию и углу наклона линии base->target, угловым направлениям base->landmark и base->target ")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Created"),
//            @ApiResponse(responseCode = "400", description = "Bad request"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
    @PostMapping
    public ResponseEntity<DirectTaskResponse> solveDirectTask(@RequestBody DirectTaskRequest directTaskRequest) {
        return new ResponseEntity<>(directTaskService.solveDirectTask(directTaskRequest), HttpStatus.CREATED);
    }
}

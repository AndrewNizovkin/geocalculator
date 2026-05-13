package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.service.ExtractService;

import java.util.List;

@RestController
@RequestMapping("extract")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExtractController {

    private final ExtractService extractService;

    @PostMapping("report")
    public ResponseEntity<List<String>> getPolygonReports(@RequestBody List<String> extractRequest) {

        return ResponseEntity.ok().body(extractService.getExtractReports(extractRequest));

    }
}

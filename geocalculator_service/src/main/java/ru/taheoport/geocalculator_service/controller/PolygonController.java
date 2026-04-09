package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.service.PolygonService;

import java.util.List;

@RestController
@RequestMapping("polygon")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PolygonController {

    private final PolygonService polygonService;

    @PostMapping("report")
    public ResponseEntity<List<String>> getPolygonReports(@RequestBody List<String> polygonRequest) {

        return ResponseEntity.ok().body(polygonService.getPolygonReports(polygonRequest));

    }





//    @PostMapping("import")
//    public ResponseEntity<List<String>> importFromTotalStation(@RequestBody List<String> importRequest) {
//
//        return ResponseEntity.ok().body(surveyService.importFromTotalStation(importRequest));
//    }


}

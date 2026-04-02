package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taheoport.geocalculator_service.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping("survey")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("import")
    public ResponseEntity<List<String>> importFromTotalStation(@RequestBody List<String> importRequest) {

        return ResponseEntity.ok().body(surveyService.importFromTotalStation(importRequest));
    }




    @PostMapping("report")
    public ResponseEntity<List<String>> getSurveyReports(@RequestBody List<String> surveyRequest) {

        return new ResponseEntity<>(surveyService.getSurveyReports(surveyRequest), HttpStatus.CREATED);
    }
}

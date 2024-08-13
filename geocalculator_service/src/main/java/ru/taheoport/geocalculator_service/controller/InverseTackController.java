package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

@RestController
@RequestMapping("inverse")
@RequiredArgsConstructor
public class InverseTackController {

    private final InverseTaskService inverseTaskService;


}

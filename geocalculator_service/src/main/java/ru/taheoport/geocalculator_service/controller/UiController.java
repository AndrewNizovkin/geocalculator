package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

@Controller
@RequestMapping("geocalculator")
@RequiredArgsConstructor
public class UiController {
    private final InverseTaskService inverseTaskService;

    /**
     * Main page of Geocalculator Service
     * @return index.html
     */
    @GetMapping
    public String home() {
        return "index";
    }

    /**
     * Resolving of Inverse Geodetic Task
     * @param model
     * @return inverse.html
     */
    @PostMapping("inverse")
    public String resolveInverseTask(Model model, @RequestBody InverseTaskRequest inverseTaskRequest) {
        model.addAttribute("inverse", inverseTaskService.getInverseTaskFullResponse(inverseTaskRequest));
        return "inverse";
    }

}

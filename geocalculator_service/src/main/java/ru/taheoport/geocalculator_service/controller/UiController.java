package ru.taheoport.geocalculator_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.taheoport.geocalculator_service.dto.DirectStringRequest;
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;
import ru.taheoport.geocalculator_service.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_service.service.DirectTaskService;
import ru.taheoport.geocalculator_service.service.InverseTaskService;

@Controller
@RequestMapping("geocalculator")
@RequiredArgsConstructor
public class UiController {
    private final InverseTaskService inverseTaskService;
    private final DirectTaskService directTaskService;

    /**
     * Main page of Geocalculator Service
     * @return index.html
     */
    @GetMapping
    public String home() {
        return "index";
    }

    /**
     * Resolves the Inverse Geodetic Task
     * @param model Model
     * @return inverse.html
     */
    @PostMapping("inverse")
    public String resolveInverseTask(Model model, @RequestBody InverseStringRequest inverseTaskRequest) {
        model.addAttribute("inverse", inverseTaskService.getInverseStringResponse(inverseTaskRequest));
        return "inverse";
    }

    /**
     * Resolves the direct geodetic task
     * @param model Model
     * @param directStringRequest DirectStringRequest
     * @return direct.html
     */
        @PostMapping("direct")
    public String resolveDirectTask(Model model, @RequestBody DirectStringRequest directStringRequest) {
        model.addAttribute("direct", directTaskService.getDirectStringResponse(directStringRequest));
        return "direct";
    }

}

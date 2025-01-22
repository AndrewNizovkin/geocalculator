package ru.taheoport.geocalculator_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.taheoport.geocalculator_web.dto.InverseTaskRequest;
import ru.taheoport.geocalculator_web.service.InverseTaskService;

@Controller
@RequestMapping("geocalculator")
@RequiredArgsConstructor
public class GeocalculatorUIController {

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
        model.addAttribute("inverse", inverseTaskService.solveInverseTask(inverseTaskRequest));
        return "inverse";
    }

//    @GetMapping("inverse")
//    public String resolveInverseTask(Model model) {
//        return "inverse";
//    }


    /**
     * Resolving of Direct Geodetic Task
     * @param model
     * @return direct.html
     */
    @GetMapping("direct")
    public String resolveDirectTask(Model model) {
        return "direct";
    }

    /**
     * Resolving of Potenot task
     * @param model
     * @return potenot.html
     */
    @GetMapping("potenot")
    public String resolvePotenotTask(Model model) {
        return "potenot";
    }
}

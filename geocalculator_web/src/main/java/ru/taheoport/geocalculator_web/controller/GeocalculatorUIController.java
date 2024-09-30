package ru.taheoport.geocalculator_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("geocalculator")
public class GeocalculatorUIController {

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
    @GetMapping("inverse")
    public String resolveInverseTask(Model model) {
        return "inverse";
    }

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

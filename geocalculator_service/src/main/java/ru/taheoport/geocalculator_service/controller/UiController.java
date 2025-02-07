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
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;
import ru.taheoport.geocalculator_service.service.DirectTaskService;
import ru.taheoport.geocalculator_service.service.InverseTaskService;
import ru.taheoport.geocalculator_service.service.PotenotService;
import ru.taheoport.geocalculator_service.validator.DirectValidator;
import ru.taheoport.geocalculator_service.validator.InverseValidator;
import ru.taheoport.geocalculator_service.validator.PotenotValidator;

import java.util.List;

@Controller
@RequestMapping("geocalculator")
@RequiredArgsConstructor
public class UiController {
    private final InverseTaskService inverseTaskService;
    private final DirectTaskService directTaskService;
    private final PotenotService potenotService;
    private final PotenotValidator potenotValidator;
    private final InverseValidator inverseValidator;
    private final DirectValidator directValidator;

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
        model.addAttribute("request", inverseTaskRequest);
        if (inverseValidator.isValidInverseStringRequest(inverseTaskRequest)) {
            model.addAttribute("response", inverseTaskService.getInverseStringResponse(inverseTaskRequest));
        } else {
            model.addAttribute("response", inverseTaskService.getInverseStringErrorResponse());
        }
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
         model.addAttribute("request", directStringRequest);
         if (directValidator.isValidDirectStringRequest(directStringRequest)) {
             model.addAttribute("response", directTaskService.getDirectStringResponse(directStringRequest));
         } else {
             model.addAttribute("response", directTaskService.getDirectStringErrorResponse());
         }
        return "direct";
    }

    /**
     * Resolves the Potenot geodetic task.
     * Determines the coordinates of the station in angular directions to landmarks with known coordinates
     * @param model Model
     * @param potenotStringRequests list of instance of PotenotStringRequest with landmark coordinates
     * @return the html code with the result of solving the inverse geodetic serif (Potenot task)
     */
    @PostMapping("potenot")
    public String resolvePotenotTask(Model model, @RequestBody List<PotenotStringRequest> potenotStringRequests) {
        model.addAttribute("request", potenotStringRequests);
        if (potenotValidator.isValidPotenotStringRequest(potenotStringRequests)) {
            model.addAttribute("response", potenotService.getPotenotStringResponse(potenotStringRequests));
        } else {
            model.addAttribute("response", potenotService.getPotenotStringErrorResponse());
        }
        return "potenot";
    }

}

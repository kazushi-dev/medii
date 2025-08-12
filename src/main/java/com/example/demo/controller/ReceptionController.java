package com.example.demo.controller;

import com.example.demo.entity.Reception;
import com.example.demo.service.ReceptionService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @GetMapping("/reception")
    public String showReception(Model model) {
        List<Reception> receptionList = receptionService.getAllReceptions();
        model.addAttribute("receptionList", receptionList);
        model.addAttribute("today", LocalDate.now());
        return "reception";
    }

    @PostMapping("/reception/add")
    public String addReception(@RequestParam("id") Long patientId, RedirectAttributes redirectAttributes) {
        if (receptionService.isAlreadyRegistered(patientId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "この患者はすでに受付済みです。");
        } else {
            receptionService.register(patientId);
        }
        return "redirect:/reception";
    }
}

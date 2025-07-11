package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceptionController {

    @Autowired
    private PatientService patientService;

    // 仮の受付リスト（今はメモリ上に保持。DB管理に切り替えてもOK）
    private List<Patient> receptionList = new ArrayList<>();

    @GetMapping("/reception")
    public String showReception(Model model) {
        model.addAttribute("receptionList", receptionList); // 「受付中のみ」表示
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("newPatient", new Patient());
        return "reception";
    }

    @PostMapping("/reception/add")
    public String addPatientToReception(@ModelAttribute("newPatient") Patient inputPatient) {
        // IDでDBから取得して受付リストに追加
        patientService.getPatientById(inputPatient.getId()).ifPresent(patient -> {
            receptionList.add(patient);
        });
        return "redirect:/reception";
    }
}

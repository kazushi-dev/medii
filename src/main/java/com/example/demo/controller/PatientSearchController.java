package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.entity.Reservation;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class PatientSearchController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients/search")
    public String show(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "date", required = false) String dateStr,
            Model model
    ) {
        LocalDate date = null;
        if (dateStr != null && !dateStr.isBlank()) {
            date = LocalDate.parse(dateStr); // 例: 2025-08-10
        }

        List<Patient> patients = (id == null && (name == null || name.isBlank()) && date == null)
                ? patientService.getAllPatients()
                : patientService.search(id, name, date);

        Map<Long, Reservation> latestMap = patientService.getLatestReservationMap(patients);

        model.addAttribute("patients", patients);
        model.addAttribute("latestMap", latestMap);
        model.addAttribute("title", "患者検索");

        // 再描画用（入力保持）
        model.addAttribute("qId", id);
        model.addAttribute("qName", name);
        model.addAttribute("qDate", dateStr);

        return "patients-search";
    }
}

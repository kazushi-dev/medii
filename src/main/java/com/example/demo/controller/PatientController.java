package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // 全件取得（JSONレスポンス）
    @GetMapping("/api")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // ID指定で1件取得（JSONレスポンス）
    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // 登録画面の表示
    @GetMapping("/new")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form"; // templates/patient-form.html
    }

    // フォームからの登録処理（Thymeleaf用）
    @PostMapping
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/reception"; // 登録後は受付画面へリダイレクト（必要なら変更可）
    }

    // 削除処理（JSON用のAPI）
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}

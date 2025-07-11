package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // 患者情報の一覧を取得
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // IDで患者情報を取得
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // 患者情報の保存
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // 患者情報の削除
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}

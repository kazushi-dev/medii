package com.example.demo.service;

import com.example.demo.entity.Reception;
import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.ReceptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceptionService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReceptionRepository receptionRepository;

    public List<Reception> getAllReceptions() {
        return receptionRepository.findAll();
    }

    public boolean isAlreadyRegistered(Long patientId) {
        return receptionRepository.findByPatient(
                patientRepository.findById(patientId).orElse(null)
        ).isPresent();
    }

    @Transactional
    public void register(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            Reception reception = new Reception();
            reception.setPatient(patient);
            receptionRepository.save(reception);
        }
    }
}

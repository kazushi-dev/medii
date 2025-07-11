package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionService {

    @Autowired
    private PatientRepository patientRepository;

    // 現在受付中の患者一覧（簡易的にメモリ上で管理）
    private List<Patient> receptionList = new ArrayList<>();

    // 受付リストを取得
    public List<Patient> getReceptionList() {
        return receptionList;
    }

    // IDで検索して受付リストに追加
    public void addPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        patient.ifPresent(p -> {
            if (!receptionList.contains(p)) {
                receptionList.add(p);
            }
        });
    }
}

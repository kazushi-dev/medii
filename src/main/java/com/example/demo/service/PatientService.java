package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * 検索：ID / 名前 / 予約日（いずれも任意）
     */
    public List<Patient> search(Long id, String name, LocalDate reservedDate) {
        // まず基本は全件
        List<Patient> base = patientRepository.findAll();

        if (id != null) {
            base = base.stream().filter(p -> Objects.equals(p.getId(), id)).collect(Collectors.toList());
        }
        if (name != null && !name.isBlank()) {
            base = base.stream()
                    .filter(p -> p.getName() != null &&
                                 p.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (reservedDate != null) {
            // 予約日が一致する患者のみ残す
            Set<Long> patientIdsWithDate = reservationRepository.findByDate(reservedDate)
                    .stream().map(r -> r.getPatient().getId())
                    .collect(Collectors.toSet());
            base = base.stream().filter(p -> patientIdsWithDate.contains(p.getId()))
                    .collect(Collectors.toList());
        }
        return base;
    }

    /** 表示用：患者ID→直近予約 のマップを作る */
    public Map<Long, Reservation> getLatestReservationMap(List<Patient> patients) {
        Map<Long, Reservation> map = new HashMap<>();
        for (Patient p : patients) {
            reservationRepository.findTopByPatientOrderByDateDesc(p)
                    .ifPresent(r -> map.put(p.getId(), r));
        }
        return map;
    }
    
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public Patient savePatient(Patient p) {
        return patientRepository.save(p);
    }

    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}

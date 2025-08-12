package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 患者の直近予約
    Optional<Reservation> findTopByPatientOrderByDateDesc(Patient patient);

    // 予約日で患者を絞り込む
    List<Reservation> findByDate(LocalDate date);

    // 患者IDで当日予約があるか
    List<Reservation> findByPatient_Id(Long patientId);
}

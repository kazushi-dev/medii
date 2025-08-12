package com.example.demo.repository;

import com.example.demo.entity.Reception;
import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    // すでにその患者IDが登録されているか確認
    Optional<Reception> findByPatient(Patient patient);
}

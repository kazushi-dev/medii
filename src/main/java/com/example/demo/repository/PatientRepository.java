package com.example.demo.repository;

import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; 

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // 名前で部分一致検索（大文字小文字を無視）
    List<Patient> findByNameContainingIgnoreCase(String name);
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    //フリガナ
    @Column(length = 100)
    private String kana;
    
    private String gender;
    private LocalDate birthDate;
    private String address;
    
    //年齢、DBに列は作らない（画面用）
    @Transient
    public Integer getAge() {
    	if (birthDate == null)return null;
    	return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // --- Getter and Setter ---
    //id, name, gender, birthDate, address, kana 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getKana() {
    	return kana;
    }
    
    public void setKana(String kana) {
    	this.kana = kana;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

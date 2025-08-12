package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//診察種別（診察ORカウ）
	@Enumerated(EnumType.STRING)
	private ReservationType type;
	
	private LocalDate date; //予約日
	
	@ManyToOne(optional = false)
	private Patient patient;
	
	//getter/setter
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public ReservationType getType() { return type; }
	public void setType(ReservationType type) { this.type = type; }
	
	public LocalDate getDate() { return date; }
	public void setDate(LocalDate date) { this.date = date; }
	
	public Patient getPatient() { return patient; }
	public void setPatient(Patient patient) { this.patient = patient;}
	
}

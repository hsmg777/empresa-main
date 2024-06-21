package com.evalueytor.empresa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Evaluacion_perito {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long id_detalleformulario;
private int cumplimiento;
private String observacion;
}

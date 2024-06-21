package com.evalueytor.empresa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Detalleformulario {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id; 
private Long id_formulario;
private Long id_matrizevaluacion;
private Long id_documento;
private int cumplimiento;
private String descripcion;
private Long id_estado;
}

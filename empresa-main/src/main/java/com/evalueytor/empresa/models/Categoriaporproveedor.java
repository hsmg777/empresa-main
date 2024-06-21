package com.evalueytor.empresa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Categoriaporproveedor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id; 
private Long id_proveedor;
private Long id_categoria;
}

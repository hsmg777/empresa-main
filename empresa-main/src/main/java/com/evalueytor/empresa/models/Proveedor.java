package com.evalueytor.empresa.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true) 
public class Proveedor extends Empresa {
    private Long calificaion;
}

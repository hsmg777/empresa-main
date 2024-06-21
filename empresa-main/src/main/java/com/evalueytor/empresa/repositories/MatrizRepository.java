package com.evalueytor.empresa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evalueytor.empresa.models.Matriz_evaluacion;

public interface MatrizRepository extends JpaRepository<Matriz_evaluacion, Long> {

}

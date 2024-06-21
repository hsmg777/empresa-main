package com.evalueytor.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalueytor.empresa.models.Evaluacion_perito;

public interface EvalPeritoRepo extends JpaRepository<Evaluacion_perito, Long> {

}

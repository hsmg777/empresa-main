package com.evalueytor.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.evalueytor.empresa.models.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long > {

}

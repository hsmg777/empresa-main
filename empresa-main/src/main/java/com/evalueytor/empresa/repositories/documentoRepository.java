package com.evalueytor.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalueytor.empresa.models.Documento;

public interface documentoRepository extends JpaRepository<Documento, Long > {

}

package com.evalueytor.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalueytor.empresa.models.Categoria;
public interface CategoriaRepository extends JpaRepository<Categoria, Long >  {

}

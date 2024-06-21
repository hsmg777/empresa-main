package com.evalueytor.empresa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalueytor.empresa.models.Categoria;
import com.evalueytor.empresa.repositories.CategoriaRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;

    // Listar todo
    @GetMapping("/findall")
    public List<Categoria> listCategoria() {
        return categoriaRepository.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaporID(@PathVariable Long id) {
        Optional<Categoria> premioOptional = categoriaRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Crear una nueva empresa
    @PostMapping("/save")
    public ResponseEntity<Categoria> crearcategoria(@RequestBody Categoria nuevoPremio) {
        Categoria premioGuardado = categoriaRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }
    // Actualizar empresa
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActual) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.map(categoria -> {
            categoria.setId(id);
            categoria.setDescripcion(categoriaActual.getDescripcion());
            Categoria categoriaActualGuardado = categoriaRepository.save(categoria);
            return new ResponseEntity<>(categoriaActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            categoriaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

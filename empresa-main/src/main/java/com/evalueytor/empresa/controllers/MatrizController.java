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

import com.evalueytor.empresa.models.Matriz_evaluacion;

import com.evalueytor.empresa.repositories.MatrizRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/matriz")
public class MatrizController {
    @Autowired
    MatrizRepository matrizRepository;
    // Listar todo
    @GetMapping("/findall")
    public List<Matriz_evaluacion> list() {
        return matrizRepository.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Matriz_evaluacion> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Matriz_evaluacion> premioOptional = matrizRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
      // Crear una nueva
      @PostMapping("/save")
      public ResponseEntity<Matriz_evaluacion> crearPremio(@RequestBody Matriz_evaluacion nuevoPremio) {
          Matriz_evaluacion premioGuardado = matrizRepository.save(nuevoPremio);
          return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
      }
    // Actualizar 
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Matriz_evaluacion> actualizarEstado(@PathVariable Long id, @RequestBody Matriz_evaluacion estadoActual) {
        Optional<Matriz_evaluacion> estadoOptional = matrizRepository.findById(id);
        return estadoOptional.map(matriz -> {
            matriz.setId(id);
            matriz.setPregunta(estadoActual.getPregunta());
            matriz.setPuntos(estadoActual.getPuntos());
            matriz.setRequiere_documento(estadoActual.getRequiere_documento());
            matriz.setIdCategoria(estadoActual.getIdCategoria());
            Matriz_evaluacion estadoActualGuardado = matrizRepository.save(matriz);
            return new ResponseEntity<>(estadoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        Optional<Matriz_evaluacion> estadoOptional = matrizRepository.findById(id);
        if (estadoOptional.isPresent()) {
            matrizRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

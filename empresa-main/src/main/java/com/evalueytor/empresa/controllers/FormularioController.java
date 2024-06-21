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
import com.evalueytor.empresa.models.Formulario;
import com.evalueytor.empresa.repositories.FormularioRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/formulario")
public class FormularioController {
@Autowired
FormularioRepository formularioRepository;
// Listar todo
    @GetMapping("/findall")
    public List<Formulario> list() {
        return formularioRepository.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Formulario> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Formulario> premioOptional = formularioRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
      // Crear una nueva
      @PostMapping("/save")
      public ResponseEntity<Formulario> crearPremio(@RequestBody Formulario nuevoPremio) {
         Formulario premioGuardado = formularioRepository.save(nuevoPremio);
          return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
      }
    // Actualizar 
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Formulario> actualizarEstado(@PathVariable Long id, @RequestBody Formulario estadoActual) {
        Optional<Formulario> estadoOptional = formularioRepository.findById(id);
        return estadoOptional.map(formulario -> {
            formulario.setId(id);
           formulario.setId_categoriaporproveedor(estadoActual.getId_categoriaporproveedor());
           formulario.setFecha(estadoActual.getFecha());
            Formulario estadoActualGuardado = formularioRepository.save(formulario);
            return new ResponseEntity<>(estadoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        Optional<Formulario> estadoOptional = formularioRepository.findById(id);
        if (estadoOptional.isPresent()) {
            formularioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

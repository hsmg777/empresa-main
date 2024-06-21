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
import com.evalueytor.empresa.models.Persona;
import com.evalueytor.empresa.repositories.PersonaRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    PersonaRepository personaRepository;
    // Listar todo
    @GetMapping("/findall")
    public List<Persona> listPersona() {
        return personaRepository.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Persona> obtenerPersonaporID(@PathVariable Long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        return personaOptional.map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
      // Crear una nueva
      @PostMapping("/save")
      public ResponseEntity<Persona> crearPersona(@RequestBody Persona nuevoPremio) {
          Persona  personaGuardado = personaRepository.save(nuevoPremio);
          return new ResponseEntity<>(personaGuardado, HttpStatus.CREATED);
      }
    // Actualizar 
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActual) {
        Optional<Persona> estadoOptional = personaRepository.findById(id);
        return estadoOptional.map(persona -> {
            persona.setId(id);
            persona.setNombre(personaActual.getNombre());
            persona.setIdentificacion(personaActual.getIdentificacion());
            Persona personaActualGuardado = personaRepository.save(persona);
            return new ResponseEntity<>(personaActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarPersonoa(@PathVariable Long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            personaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

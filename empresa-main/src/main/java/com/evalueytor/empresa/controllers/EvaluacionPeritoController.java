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

import com.evalueytor.empresa.models.Evaluacion_perito;
import com.evalueytor.empresa.repositories.EvalPeritoRepo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/evalPerito")
public class EvaluacionPeritoController {
@Autowired
    EvalPeritoRepo evalPeritoRepo;
    // Listar todo
    @GetMapping("/findall")
    public List<Evaluacion_perito> list() {
        return evalPeritoRepo.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Evaluacion_perito> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Evaluacion_perito> premioOptional = evalPeritoRepo.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
      // Crear una nueva
      @PostMapping("/save")
      public ResponseEntity<Evaluacion_perito> crearPremio(@RequestBody Evaluacion_perito nuevoPremio) {
          Evaluacion_perito premioGuardado = evalPeritoRepo.save(nuevoPremio);
          return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
      }
    // Actualizar 
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Evaluacion_perito> actualizarEstado(@PathVariable Long id, @RequestBody Evaluacion_perito estadoActual) {
        Optional<Evaluacion_perito> estadoOptional = evalPeritoRepo.findById(id);
        return estadoOptional.map(eval -> {
            eval.setId(id);
            eval.setCumplimiento(estadoActual.getCumplimiento());
            eval.setObservacion(estadoActual.getObservacion());
            eval.setId_detalleformulario(estadoActual.getId_detalleformulario());
            Evaluacion_perito estadoActualGuardado = evalPeritoRepo.save(eval);
            return new ResponseEntity<>(estadoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        Optional<Evaluacion_perito> estadoOptional = evalPeritoRepo.findById(id);
        if (estadoOptional.isPresent()) {
            evalPeritoRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

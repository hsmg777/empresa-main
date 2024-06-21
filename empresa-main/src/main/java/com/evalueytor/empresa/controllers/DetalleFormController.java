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

import com.evalueytor.empresa.models.Detalleformulario;
import com.evalueytor.empresa.repositories.DetalleFormRepo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/detalleform")
public class DetalleFormController {
@Autowired
DetalleFormRepo detalleFormRepo;
// Listar todo
    @GetMapping("/findall")
    public List<Detalleformulario> list() {
        return detalleFormRepo.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Detalleformulario> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Detalleformulario> premioOptional = detalleFormRepo.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Crear una nueva empresa
    @PostMapping("/save")
    public ResponseEntity<Detalleformulario> crearPremio(@RequestBody Detalleformulario nuevoPremio) {
        Detalleformulario premioGuardado = detalleFormRepo.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }
    // Actualizar empresa
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Detalleformulario> actualizarEmopresa(@PathVariable Long id, @RequestBody Detalleformulario documentoActual) {
        Optional<Detalleformulario> documentoOptional = detalleFormRepo.findById(id);
        return documentoOptional.map(detalle -> {
            detalle.setId(id);
           detalle.setId_documento(documentoActual.getId_documento());
           detalle.setId_estado(documentoActual.getId_estado());
           detalle.setId_formulario(documentoActual.getId_formulario());
           detalle.setId_matrizevaluacion(documentoActual.getId_matrizevaluacion());
           detalle.setCumplimiento(documentoActual.getCumplimiento());
           detalle.setDescripcion(documentoActual.getDescripcion());
            Detalleformulario documentoActualGuardado = detalleFormRepo.save(detalle);
            return new ResponseEntity<>(documentoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long id) {
        Optional<Detalleformulario> documentoOptional = detalleFormRepo.findById(id);
        if (documentoOptional.isPresent()) {
            detalleFormRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.evalueytor.empresa.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.evalueytor.empresa.models.Documento;
import com.evalueytor.empresa.repositories.documentoRepository;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/documento")
public class DocumentoController {
    @Autowired
    documentoRepository documentoRepository;
    // Listar todo
    @GetMapping("/findall")
    public List<Documento> list() {
        return documentoRepository.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Documento> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Documento> premioOptional = documentoRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Crear una nueva empresa
    @PostMapping("/save")
    public ResponseEntity<Documento> crearPremio(@RequestBody Documento nuevoPremio) {
        Documento premioGuardado = documentoRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }
    // Actualizar empresa
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Documento> actualizarEmopresa(@PathVariable Long id, @RequestBody Documento documentoActual) {
        Optional<Documento> documentoOptional = documentoRepository.findById(id);
        return documentoOptional.map(documento -> {
            documento.setId(id);
            documento.setNombre(documentoActual.getNombre());
            documento.setUrl(documentoActual.getUrl());
            Documento documentoActualGuardado = documentoRepository.save(documento);
            return new ResponseEntity<>(documentoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long id) {
        Optional<Documento> documentoOptional = documentoRepository.findById(id);
        if (documentoOptional.isPresent()) {
            documentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

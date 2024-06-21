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
import com.evalueytor.empresa.models.Categoriaporproveedor;
import com.evalueytor.empresa.repositories.CategoProveRepo;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/categoriaProveedor")
public class CategoriaProveedorController {
@Autowired
CategoProveRepo categoProveRepo;
// Listar todo
    @GetMapping("/findall")
    public List<Categoriaporproveedor> list() {
        return categoProveRepo.findAll();
    }
    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Categoriaporproveedor> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Categoriaporproveedor> premioOptional = categoProveRepo.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Crear una nueva empresa
    @PostMapping("/save")
    public ResponseEntity<Categoriaporproveedor> crearPremio(@RequestBody Categoriaporproveedor nuevoPremio) {
        Categoriaporproveedor premioGuardado = categoProveRepo.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }
    // Actualizar empresa
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Categoriaporproveedor> actualizarEmopresa(@PathVariable Long id, @RequestBody Categoriaporproveedor documentoActual) {
        Optional<Categoriaporproveedor> documentoOptional = categoProveRepo.findById(id);
        return documentoOptional.map(category -> {
            category.setId(id);
            category.setId_categoria(documentoActual.getId_categoria());
            category.setId_proveedor(documentoActual.getId_proveedor());
            Categoriaporproveedor documentoActualGuardado = categoProveRepo.save(category);
            return new ResponseEntity<>(documentoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long id) {
        Optional<Categoriaporproveedor> documentoOptional = categoProveRepo.findById(id);
        if (documentoOptional.isPresent()) {
            categoProveRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

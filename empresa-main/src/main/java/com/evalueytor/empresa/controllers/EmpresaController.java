package com.evalueytor.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.evalueytor.empresa.repositories.EmpresaRepository;
import com.evalueytor.empresa.repositories.ProveedorRepository;
import com.evalueytor.empresa.models.Empresa;
import com.evalueytor.empresa.models.Proveedor;

@RestController
@RequestMapping("/api/herencia")
public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired 
    ProveedorRepository proveedorRepository;

    // Listar todo
    @GetMapping("/empresa/findall")
    public List<Empresa> list() {
        return empresaRepository.findAll();
    }

    // Listar por Id
    @GetMapping("/empresa/findbyid/{id}")
    public ResponseEntity<Empresa> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Empresa> premioOptional = empresaRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva empresa
    @PostMapping("/empresa/save")
    public ResponseEntity<Empresa> crearPremio(@RequestBody Empresa nuevoPremio) {
        Empresa premioGuardado = empresaRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }

    // Actualizar empresa
    @PutMapping("/empresa/updatebyid/{id}")
    public ResponseEntity<Empresa> actualizarEmopresa(@PathVariable Long id, @RequestBody Empresa empresaActual) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        return empresaOptional.map(empresa -> {
            empresa.setId(id);
            empresa.setNombre(empresaActual.getNombre());
            empresa.setDireccion(empresaActual.getDireccion());
            Empresa empresaActualGuardado = empresaRepository.save(empresa);
            return new ResponseEntity<>(empresaActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un empresa por ID
    @DeleteMapping("/empresa/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/proveedor/findall")
    public List<Proveedor> listProveedor() {
        return proveedorRepository.findAll();
    }
    @GetMapping("/proveedor/findbyid/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
        Optional<Proveedor> premioOptional = proveedorRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/proveedor/save")
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor nuevoPremio) {
        Proveedor premioGuardado = proveedorRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }
    @PutMapping("/proveedor/updatebyid/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorActual) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        return proveedorOptional.map(proveedor -> {
            proveedor.setId(id);
            proveedor.setCalificaion(proveedorActual.getCalificaion());
            Proveedor proveedorActualGuardado = proveedorRepository.save(proveedor);
            return new ResponseEntity<>(proveedorActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/proveedor/deletebyid/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            proveedorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

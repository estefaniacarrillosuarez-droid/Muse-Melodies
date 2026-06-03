package com.musemelodies.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musemelodies.api.model.Genero;
import com.musemelodies.api.service.GeneroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/generos")
public class GeneroController {

    private final GeneroService generoService;

    // Inyección del Servicio
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    // GET /api/v1/generos -> 200 OK + lista (Público)
    @GetMapping
    public ResponseEntity<List<Genero>> listarGeneros() {
        return ResponseEntity.ok(generoService.listAll());
    }

    // GET /api/v1/generos/{id} -> 200 OK / 404 Not Found (Público)
    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarPorId(@PathVariable Long id) {
        return generoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/generos -> 201 Created (Requiere API Key)
    @PostMapping
    public ResponseEntity<Genero> crearGenero(@Valid @RequestBody Genero genero) {
        Genero nuevoGenero = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGenero);
    }

    // DELETE /api/v1/generos/{id} -> 204 No Content / 404 (Requiere API Key)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable Long id) {
        if (generoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
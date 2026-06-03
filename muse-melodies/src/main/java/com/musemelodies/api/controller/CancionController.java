package com.musemelodies.api.controller;

import com.musemelodies.api.model.Cancion;
import com.musemelodies.api.service.CancionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/canciones")
public class CancionController {

    private final CancionService cancionService;

    // El Controller NUNCA accede directamente al Repository (Requisito RA9)
    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    // GET /api/v1/canciones -> 200 OK + lista
    @GetMapping
    public ResponseEntity<List<Cancion>> listarCanciones() {
        return ResponseEntity.ok(cancionService.listAll());
    }

    // Filtrar de forma dinámica en Postman
    @GetMapping("/filtrar")
    public ResponseEntity<List<Cancion>> filtrarCanciones(
            @RequestParam(required = false) String album,
            @RequestParam(required = false) String paisArtista) {
        
        if (album != null) {
            return ResponseEntity.ok(cancionService.buscarPorAlbum(album));
        }
        if (paisArtista != null) {
            return ResponseEntity.ok(cancionService.buscarExplicitasPorPais(paisArtista));
        }
        return ResponseEntity.badRequest().build();
    }

    // GET /api/v1/canciones/{id} -> 200 OK / 404 Not Found
    // Uso de .map().orElse() sin usar .get() directamente (Requisito RA9 d)
    @GetMapping("/{id}")
    public ResponseEntity<Cancion> buscarPorId(@PathVariable Long id) {
        return cancionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/canciones -> 201 Created
    @PostMapping
    public ResponseEntity<Cancion> crearCancion(@Valid @RequestBody Cancion cancion) {
        Cancion nuevaCancion = cancionService.save(cancion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancion);
    }

    // PUT /api/v1/canciones/{id} -> 200 OK / 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> actualizarCancion(@PathVariable Long id, @Valid @RequestBody Cancion cancionDetalles) {
        return cancionService.update(id, cancionDetalles)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/canciones/{id} -> 204 No Content / 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Long id) {
        if (cancionService.delete(id)) {
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        }
        return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
    }
}
package com.musemelodies.api.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musemelodies.api.model.Artista;
import com.musemelodies.api.service.ArtistaService;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    // Constructor.
    // Permite utilizar las operaciones definidas en el servicio de artistas.
    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    // Devuelve la lista completa de artistas.
    @GetMapping
    public ResponseEntity<List<Artista>> listarArtistas() {
        return ResponseEntity.ok(artistaService.listAll());
    }

    // Busca artistas cuyo nombre contenga el texto indicado.
    // También permite ordenar los resultados por el campo seleccionado.
    @GetMapping("/buscar")
    public ResponseEntity<List<Artista>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {

        Sort.Direction direccion =
                order.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Sort sort = Sort.by(direccion, sortBy);

        return ResponseEntity.ok(
                artistaService.findByNombre(nombre, sort)
        );
    }

    // Busca un artista por su ID.
    // Devuelve 404 si no existe.
    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
        return artistaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea un nuevo artista utilizando los datos recibidos.
    @PostMapping
    public ResponseEntity<Artista> crearArtista(@RequestBody Artista artista) {
        Artista nuevoArtista = artistaService.save(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArtista);
    }

    // Actualiza los datos de un artista existente.
    // Si el artista no existe, devuelve un error 404.
    @PutMapping("/{id}")
    public ResponseEntity<Artista> actualizarArtista(
            @PathVariable Long id,
            @RequestBody Artista artistaDetalles
    ) {

        return artistaService.update(id, artistaDetalles)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Elimina un artista utilizando su ID.
    // Devuelve 204 si se elimina correctamente.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable Long id) {

        if (artistaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
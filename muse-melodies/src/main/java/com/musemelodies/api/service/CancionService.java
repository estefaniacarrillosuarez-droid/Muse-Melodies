package com.musemelodies.api.service;

import com.musemelodies.api.model.Cancion;
import com.musemelodies.api.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service marca esta clase como un componente de lógica de negocio.
// Spring la detecta automáticamente y la registra en el contexto.
@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    // Inyección por constructor
    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    // Devuelve todas las canciones de la base de datos
    public List<Cancion> listAll() {
        return cancionRepository.findAll();
    }

    // Busca una canción por su ID. Devuelve Optional para manejar el caso de "no encontrada"
    public Optional<Cancion> findById(Long id) {
        return cancionRepository.findById(id);
    }

    // Guarda una nueva canción en la base de datos
    public Cancion save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    // Actualiza una canción existente. Si no existe, devuelve Optional vacío
    public Optional<Cancion> update(Long id, Cancion cancionDetalles) {
        return cancionRepository.findById(id)
            .map(cancionExistente -> {
                cancionExistente.setTitulo(cancionDetalles.getTitulo());
                cancionExistente.setArtista(cancionDetalles.getArtista());
                cancionExistente.setAlbum(cancionDetalles.getAlbum());
                cancionExistente.setDuracionSegundos(cancionDetalles.getDuracionSegundos());
                cancionExistente.setExplicita(cancionDetalles.getExplicita());
                return cancionRepository.save(cancionExistente);
            });
    }

    // Elimina una canción por su ID. Devuelve true si existía, false si no
    public boolean delete(Long id) {
        if (cancionRepository.existsById(id)) {
            cancionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
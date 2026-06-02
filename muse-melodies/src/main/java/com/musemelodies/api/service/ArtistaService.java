package com.musemelodies.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.musemelodies.api.model.Artista;
import com.musemelodies.api.repository.ArtistaRepository;


@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    // Constructor.
    // Permite utilizar las operaciones del repositorio de artistas.
    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    // Devuelve todos los artistas guardados en la base de datos.
    public List<Artista> listAll() {
        return artistaRepository.findAll();
    }

    // Busca un artista por ID.
    // Puede devolver un artista o ningún resultado.
    public Optional<Artista> findById(Long id) {
        return artistaRepository.findById(id);
    }

    // Busca artistas cuyo nombre contenga el texto indicado.
    // También permite ordenar los resultados.
    public List<Artista> findByNombre(String nombre, Sort sort) {
        return artistaRepository.findByNombreContainingIgnoreCase(nombre, sort);
    }

    // Guarda un nuevo artista en la base de datos.
    public Artista save(Artista artista) {
        return artistaRepository.save(artista);
    }

    // Actualiza un artista existente. Si no existe, devuelve Optional vacío.
    public Optional<Artista> update(Long id, Artista datosActualizados) {
        return artistaRepository.findById(id)
                .map(artistaExistente -> {
                    artistaExistente.setNombre(datosActualizados.getNombre());
                    artistaExistente.setPaisOrigen(datosActualizados.getPaisOrigen());
                    artistaExistente.setAnioDebut(datosActualizados.getAnioDebut());
                    artistaExistente.setGeneros(datosActualizados.getGeneros());

                    return artistaRepository.save(artistaExistente);
                });
    }

    // Elimina un artista por su ID. Devuelve true si existía, false si no
    public boolean delete(Long id) {
        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
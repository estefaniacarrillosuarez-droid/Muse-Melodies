package com.musemelodies.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musemelodies.api.model.Genero;
import com.musemelodies.api.repository.GeneroRepository;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    // Devuelve todos los géneros
    public List<Genero> listAll() {
        return generoRepository.findAll();
    }

    // Busca un género por su ID
    public Optional<Genero> findById(Long id) {
        return generoRepository.findById(id);
    }

    // Guarda o crea un nuevo género
    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }

    // Elimina un género por su ID
    public boolean delete(Long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
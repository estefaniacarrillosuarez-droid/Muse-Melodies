package com.musemelodies.api.repository;

import com.musemelodies.api.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    // findAll(), findById(), save(), deleteById(), etc.
}
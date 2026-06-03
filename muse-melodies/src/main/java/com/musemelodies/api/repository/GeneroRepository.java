package com.musemelodies.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musemelodies.api.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    
}
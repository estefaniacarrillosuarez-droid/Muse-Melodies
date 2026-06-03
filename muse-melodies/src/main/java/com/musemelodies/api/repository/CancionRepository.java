package com.musemelodies.api.repository;

import com.musemelodies.api.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    // Buscar canciones por álbum ignorando mayúsculas/minúsculas
    List<Cancion> findByAlbumContainingIgnoreCase(String album);

    // Consulta JPQL que navega por la relación para buscar canciones explícitas de artistas de un país concreto
    @Query("SELECT c FROM Cancion c WHERE c.explicita = true AND LOWER(c.artista.paisOrigen) = LOWER(:pais)")
    List<Cancion> findCancionesExplicitasPorPais(@Param("pais") String pais);
}
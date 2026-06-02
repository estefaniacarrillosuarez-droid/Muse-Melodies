package com.musemelodies.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.musemelodies.api.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    // Busca artistas cuyo nombre contenga el texto indicado.
    // Nos permite implementar búsquedas nombre y ordenar los resultados.
    List<Artista> findByNombreContainingIgnoreCase(
            String nombre,
            Sort sort
    );

    // Busca artistas por país de origen.
    // Puede utilizarse para filtrar artistas según su procedencia.
    List<Artista> findByPaisOrigenContainingIgnoreCase(
            String pais
    );

    // Devuelve los artistas que debutaron a partir del año indicado.
    // Nos permite realizar búsquedas por rango de años.
    @Query("SELECT a FROM Artista a WHERE a.anioDebut >= :anio")
    List<Artista> findArtistasDesdeAnio(
            @Param("anio") Integer anio
    );

    // Cuenta cuántas canciones tiene un artista.
    // Nos permite obtener información adicional sobre cada artista sin tener que recuperar toda la lista de canciones.
    @Query("SELECT COUNT(c) FROM Cancion c WHERE c.artista.id = :artistaId")
    Long contarCancionesPorArtista(
            @Param("artistaId") Long artistaId
    );
}
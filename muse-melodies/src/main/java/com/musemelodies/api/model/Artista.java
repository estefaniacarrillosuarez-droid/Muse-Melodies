package com.musemelodies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Artista {
    
    // Identificador único del artista.
    // Cada artista tendrá un id diferente generado automáticamente por la base de datos al crearse.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nombre del artista.
    // Es un campo obligatorio y debe tener entre 2 y 100 caracteres.
    // No se permitirá guardar artistas sin nombre.
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;
    
    // País de origen del artista.
    // Se utiliza para poder filtrar artistas por procedencia.
    @Size(max = 50, message = "El país no puede superar 50 caracteres")
    @Column(name = "pais_origen", length = 50)
    private String paisOrigen;
    
    // Año en el que el artista debutó.
    // Las validaciones evitan introducir años poco realistas.
    @Min(value = 1900, message = "El año de debut no puede ser anterior a 1900")
    @Max(value = 2100, message = "El año de debut no puede ser posterior a 2100")
    private Integer anioDebut;
    
    // Relación uno a muchos con Cancion.
    // Un artista puede tener varias canciones asociadas.
    // mappedBy indica que la relación se gestiona desde la entidad Cancion.
    // JsonIgnore evita que al devolver un artista en JSON se produzcan bucles infinitos entre Artista y Cancion.
    // orphanRemoval elimina automáticamente canciones que dejan de estar asociadas al artista.
    @JsonIgnore
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<Cancion> canciones = new ArrayList<>();
    
    // Relación mucho a muchos con Genero.
    // Un artista puede pertenecer a varios géneros musicales y un mismo género puede estar asociado a varios artistas.
    // Se crea una tabla intermedia llamada artista_genero para almacenar las relaciones.
    // Se utiliza Set para evitar géneros duplicados.
    @ManyToMany
    @JoinTable(
            name = "artista_genero",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    @ToString.Exclude
    @Builder.Default
    private Set<Genero> generos = new HashSet<>();
}

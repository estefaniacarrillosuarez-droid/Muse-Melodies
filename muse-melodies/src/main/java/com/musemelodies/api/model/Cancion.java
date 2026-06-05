package com.musemelodies.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "canciones")
@Data // Getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos

public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TÍTULO: No puede estar vacío y debe estar entre 1-100 caracteres.
    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 1, max = 100, message = "El titulo debe tener entre 1 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    // ARTISTA: Muchas canciones pueden estar atribuidas a un artista.
    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    @JsonIgnoreProperties("canciones") // Evita bucles en el JSON
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Artista artista;

    // ÁLBUM: Debe estar entre 1-100 caracteres.
    @Size(min = 1, max = 100, message = "El album debe tener entre 1 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String album;

    // DURACIÓN (segundos): No puede estar vacío y debe ser de entre 1-10 minutos.
    @NotNull(message = "La duracion no puede estar vacia")
    @Min(value = 60, message = "La duracion no puede ser menor a un minuto (60 segundos)")
    @Max(value = 600, message = "La duracion no puede ser mayor a 10 minutos (600 segundos)")
    @Column(name = "duracion_segundos")
    private Integer duracionSegundos;
    
    // EXPLICIDAD: No puede estar vacío.
    @NotNull(message = "Tiene que indicarse si la cancion es explicita o no")
    private Boolean explicita;
}
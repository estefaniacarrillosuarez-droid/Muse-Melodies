package com.musemelodies.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    private Artista artista; //O si son muchos artistas sería:

    @Size(min = 1, max = 100, message = "El álbum debe tener entre 1 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String album;

    @NotBlank(message = "La duración no puede estar vacía")
    @Min(value = 60, message = "La duración no puede ser menor a un minuto")
    @Max(value = 600, message = "La duración no puede ser mayor a 10 minutos")
    private Integer duracionSegundos;

    @NotBlank(message = "Tiene que indicarse si la canción es explícita o no")
    private Boolean explicita;
}
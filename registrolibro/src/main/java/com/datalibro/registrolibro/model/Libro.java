package com.datalibro.registrolibro.model;

// import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "libro")
@Data // genera getters, setters, etc, todo para que las variables sean usables
@AllArgsConstructor // genera costructor
@NoArgsConstructor // tambien genera constructor

public class Libro {

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = true)
    private String fecha; 

    @Id
    @Column(unique = true, length = 5, nullable = false)
    private Integer codigo;

    @Column(nullable = false)
    private Integer isbn;

    @Column(nullable = false, length = 1)
    private Integer disponible;
}
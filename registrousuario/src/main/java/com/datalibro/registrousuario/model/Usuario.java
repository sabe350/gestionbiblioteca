package com.datalibro.registrousuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "usuario")
@Data // genera getters, setters, etc, todo para que las variables sean usables
@AllArgsConstructor // genera costructor
@NoArgsConstructor // tambien genera constructor

public class Usuario {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String password; // contraseña

    @Id
    @Column(unique = true, length = 9, nullable = false)
    private Integer rut;

    @Column(nullable = false)
    private String email;
}
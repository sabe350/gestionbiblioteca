package com.datalibro.registrolibrousr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "pedido")
@Data // genera getters, setters, etc, todo para que las variables sean usables
@AllArgsConstructor // genera costructor
@NoArgsConstructor // tambien genera constructor

public class Pedido {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String fechaini; 

    @Column(nullable = false)
    private String fechadevo; 

    @Column(unique = false, length = 9, nullable = false)
    private Integer rut;

    @Id
    @Column(unique = true, nullable = false)
    private Integer codigo;
}
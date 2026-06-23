package com.datalibro.registrolibro.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LibroDTO {

    private String titulo;
    private String autor;
    private String fecha;
    private Integer codigo;
    private Integer isbn;
    private Integer disponible;
}

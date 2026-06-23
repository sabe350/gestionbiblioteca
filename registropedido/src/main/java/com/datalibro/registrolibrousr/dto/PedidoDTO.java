package com.datalibro.registrolibrousr.dto;

import lombok.Data;

@Data
public class PedidoDTO {

    private String nombre;
    private String titulo;
    private String fechaini;
    private String fechadevo;
    private Integer rut;
    private Integer codigo;
}

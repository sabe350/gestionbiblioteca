package com.datalibro.registrousuario.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private String nombre;
    private String apellido;
    private String password;
    private Integer rut;
    private String email;
}

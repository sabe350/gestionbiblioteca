package com.datalibro.registrolibro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path="/api/v1/admin")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Prueba de administrador", description = "Prueba de privilegios de administrador")
public class AdminController {

    @GetMapping(value="/test")
    @Operation(summary = "Prueba de privilegios", description = "Muestra mensaje si usuario tiene privilegios de administrador")
    public String admin(){
        return "Acceso solo ADMIN";
    }
    
}

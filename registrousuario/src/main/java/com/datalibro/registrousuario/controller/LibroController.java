package com.datalibro.registrousuario.controller;

import java.security.DrbgParameters.Reseed;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.datalibro.registrousuario.service.LibroClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v8/libro")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Busqueda remota de libros", description = "Operaciones relacionadas con la busqueda remota de libros en el sistema de biblioteca")

public class LibroController {

    private final LibroClient libroClient;

    public LibroController(LibroClient libroClient){
        this.libroClient = libroClient;
        
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Busca libro", description = "Busca libro en base de datos por codigo")
    public String verLibro(@PathVariable Long codigo){
        return "Libro " + codigo + ": " + libroClient.getLibro(codigo);
    }
}

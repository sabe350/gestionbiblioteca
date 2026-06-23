package com.datalibro.registrolibro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datalibro.registrolibro.dto.LibroDTO;
import com.datalibro.registrolibro.service.CargaMasivaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/api/v1/libro")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Carga masiva", description = "Operaciones relacionadas con pruebas de carga masiva de datos")
public class CargaController {

    @Autowired
    private CargaMasivaService service;

    @PostMapping(value="/masivo")
    @Operation(summary = "Carga masiva", description = "Ejecuta script de carga masiva")
    public ResponseEntity<?> carga(@RequestBody List<LibroDTO> libros){
        try{
            if (libros == null || libros.isEmpty()){
                return ResponseEntity.badRequest().body("La lista esta vacia");
            }

            long inicio = System.currentTimeMillis();
            service.procesarCarga(libros);
            long fin = System.currentTimeMillis();

            return ResponseEntity.ok("Exito: " + libros.size() + " procesados en " + (fin - inicio) + "ms");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la carga: " + e.getMessage());
        }
    }
}

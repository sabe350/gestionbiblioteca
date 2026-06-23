package com.datalibro.registrolibro.controller;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/api/v1/admin/db")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Restauracion de base de datos", description = "Operaciones relacionadas a la restauracion de la base de datos")
public class DatabaseAdminController {


    private final Flyway flyway;
    
    public DatabaseAdminController(Flyway flyway){
        this.flyway = flyway;
    }
    @PostMapping("/repair")
    @Operation(summary = "Reparar base de datos", description = "Restaura la base de datos desde un punto de restauracion previo")
    public String repairDatabase(){
        flyway.repair();
        return "Historial de Flyaway reparado";
    }
}

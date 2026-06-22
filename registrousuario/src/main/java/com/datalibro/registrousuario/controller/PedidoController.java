package com.datalibro.registrousuario.controller;

import java.security.DrbgParameters.Reseed;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.datalibro.registrousuario.dto.PedidoDTO;
import com.datalibro.registrousuario.service.PedidoClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v8/pedido")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Registro remoto de pedidos", description = "Operaciones relacionadas con el registor de pedidos de manera remota al sistema de biblioteca")

public class PedidoController {

    private final PedidoClient pedidoClient;

    public PedidoController(PedidoClient pedidoClient){
        this.pedidoClient = pedidoClient;
        
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Busca pedido", description = "Busca pedido en base de datos por codigo de libro")
    public String verPedido(@PathVariable Long codigo){
        return "Pedido de " + codigo + ": " + pedidoClient.getPedido(codigo);
    }

    @PostMapping
    @Operation(summary = "Agrega pedido", description = "Agrega pedido a base de datos")
    public PedidoDTO create(@RequestBody PedidoDTO pedido){
        return pedidoClient.create(pedido);
    }

    
}

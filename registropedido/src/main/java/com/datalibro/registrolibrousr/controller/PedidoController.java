package com.datalibro.registrolibrousr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.datalibro.registrolibrousr.model.Pedido;
import com.datalibro.registrolibrousr.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/api/v1/pedido")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Registro remoto de pedidos", description = "Operaciones relacionadas con el registor de pedidos al sistema de biblioteca") 
public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping
    @Operation(summary = "Lista todos los pedidos", description = "Lista todos los pedidos en la base de datos")
    public ResponseEntity<List<Pedido>> mostrar(){
        List<Pedido> pedidos = service.findall();
        if (pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    @Operation(summary = "Agrega pedido", description = "Agrega pedido a base de datos")
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido){
        Pedido nuevoPedido = service.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }
    
    @GetMapping(value="/{codigo}")
    @Operation(summary = "Busca pedido", description = "Busca pedido en base de datos por codigo de libro")
    public ResponseEntity<Pedido> search(@PathVariable Integer codigo){
        try{
            Pedido pedido = service.findById(codigo);
            return ResponseEntity.ok(pedido);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping(value="/{codigo}")
    @Operation(summary = "Actualizar pedido", description = "Actualiza los datos del pedido seleccionado")
    public ResponseEntity<Pedido> upd(@PathVariable Integer codigo, @RequestBody Pedido pedido){
        try{
            Pedido ped = service.findById(codigo);
            ped.setCodigo(codigo);
            ped.setNombre(pedido.getNombre());
            ped.setTitulo(pedido.getTitulo());
            ped.setFechaini(pedido.getFechaini());
            ped.setFechadevo(pedido.getFechadevo());
            ped.setRut(pedido.getRut());
            
            service.save(ped);
            return ResponseEntity.ok(pedido);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{codigo}")
    @Operation(summary = "Eliminar pedido", description = "Elimina pedido correspondiente al codigo ingresado")
    public ResponseEntity<?> del(@PathVariable long codigo){
        try{
            service.delete(codigo);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

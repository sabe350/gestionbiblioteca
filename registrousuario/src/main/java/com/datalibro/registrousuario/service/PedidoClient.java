package com.datalibro.registrousuario.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.datalibro.registrousuario.dto.PedidoDTO;

@FeignClient(name= "pedido-service", url="http://localhost:8081/api/v1")
public interface PedidoClient {

    @GetMapping("/pedido/{codigo}")
    String getPedido(@PathVariable("codigo") Long codigo);

    @PostMapping("/pedido")
    PedidoDTO create(@RequestBody PedidoDTO pedidoDTO);

    

}

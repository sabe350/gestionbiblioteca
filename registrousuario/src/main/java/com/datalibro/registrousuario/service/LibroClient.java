package com.datalibro.registrousuario.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "libro-service", url="http://localhost:8082/api/v1")
public interface LibroClient {

    @GetMapping("/libro/{codigo}")
    String getLibro(@PathVariable("codigo") Long codigo);

}

package com.datalibro.registrousuario.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.datalibro.registrousuario.model.Usuario;
import com.datalibro.registrousuario.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setRut(123456789);
        usuario.setNombre("prueba");
        usuario.setApellido("prueba");
        usuario.setPassword("prueba");
        usuario.setEmail("prueba@prueba.com");
    }

    @Test
    public void testGetAllUsuarios() throws Exception{
        mockMvc.perform(get("/api/v8/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rut").value(123456789))
                .andExpect(jsonPath("$[0].nombre").value("prueba"));

    }
}

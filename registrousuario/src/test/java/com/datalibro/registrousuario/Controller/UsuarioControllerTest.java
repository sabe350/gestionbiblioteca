package com.datalibro.registrousuario.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.datalibro.registrousuario.controller.UsuarioController;
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
        when(usuarioService.findall()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/v8/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rut").value(123456789))
                .andExpect(jsonPath("$[0].nombre").value("prueba"))
                .andExpect(jsonPath("$[0].apellido").value("prueba"))
                .andExpect(jsonPath("$[0].password").value("prueba"))
                .andExpect(jsonPath("$[0].email").value("prueba@prueba.com"));

    }

    @Test
    public void testGetUsuarioByRut() throws Exception{
        when(usuarioService.findById(123456789)).thenReturn(usuario);

        mockMvc.perform(get("/api/v8/usuario/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value(123456789))
                .andExpect(jsonPath("$.nombre").value("prueba"))
                .andExpect(jsonPath("$.apellido").value("prueba"))
                .andExpect(jsonPath("$.password").value("prueba"))
                .andExpect(jsonPath("$.email").value("prueba@prueba.com"));
    }

    @Test
    public void testCreateUsuario() throws Exception{
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);
    
        mockMvc.perform(post("/api/v8/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rut").value(123456789))
            .andExpect(jsonPath("$.nombre").value("prueba"))
            .andExpect(jsonPath("$.apellido").value("prueba"))
            .andExpect(jsonPath("$.password").value("prueba"))
            .andExpect(jsonPath("$.email").value("prueba@prueba.com"));
    }

    @Test
    public void testUpdateUsuario() throws Exception{
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/v8/usuario/123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rut").value(123456789))
            .andExpect(jsonPath("$.nombre").value("prueba"))
            .andExpect(jsonPath("$.apellido").value("prueba"))
            .andExpect(jsonPath("$.password").value("prueba"))
            .andExpect(jsonPath("$.email").value("prueba@prueba.com"));
    }

    @Test
    public void testDeleteUsuario() throws Exception{
        doNothing().when(usuarioService).delete( 123456789);
        
        mockMvc.perform(delete("/api/v8/usuario/123456789"))
                .andExpect(status().isOk());
        
        verify(usuarioService, times(1)).delete(123456789);
    }
}


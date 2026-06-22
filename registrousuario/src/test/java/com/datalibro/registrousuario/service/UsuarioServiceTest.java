package com.datalibro.registrousuario.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.datalibro.registrousuario.model.Usuario;
import com.datalibro.registrousuario.repository.UsuarioRepository;
import com.datalibro.registrousuario.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll(){
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario("prueba", "prueba", "prueba1", 1, "prueba@prueba.com")));

        List<Usuario> usuarios = usuarioService.findall();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindByRut(){
        Integer rut = 123456789;
        Usuario usuario = new Usuario("prueba", "prueba", "prueba", rut, "prueba@prueba.com");

        when(usuarioRepository.findById((long) rut)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(rut);

        assertNotNull(found);
        assertEquals(rut, found.getRut());
    }

    @Test
    public void testSave(){
        Usuario usuario = new Usuario("prueba", "prueba", "prueba", 1, "prueba@prueba.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario guardado = usuarioService.save(usuario);

        assertNotNull(guardado);
        assertEquals("prueba", guardado.getNombre());
    }

    @Test
    public void testDeleteByRut(){
        Integer rut = 123456789;

        doNothing().when(usuarioRepository).deleteById((long) rut);

        usuarioService.delete(rut);

        verify(usuarioRepository, times(1)).deleteById((long) rut);
    }


}

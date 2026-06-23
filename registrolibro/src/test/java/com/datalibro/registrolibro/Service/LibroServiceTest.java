package com.datalibro.registrolibro.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.datalibro.registrolibro.model.Libro;
import com.datalibro.registrolibro.repository.LibroRepository;
import com.datalibro.registrolibro.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LibroServiceTest {

    @Autowired
    private LibroService libroService;

    @MockitoBean
    private LibroRepository libroRepository;

    @Test
    public void testFindAll(){
        when(libroRepository.findAll()).thenReturn(List.of(new Libro("prueba", "prueba", "00/00/0000", 1, 123, 1)));

        List<Libro> libros = libroService.findall();

        assertNotNull(libros);
        assertEquals(1, libros.size());
    }

    @Test
    public void testFindByCodigo(){
        Integer codigo = 1;
        Libro libro = new Libro("prueba", "prueba", "00/00/0000", 1, 123, 1);

        when(libroRepository.findById((long) codigo)).thenReturn(Optional.of(libro));

        Libro found = libroService.findById(codigo);

        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave(){
        Libro libro = new Libro("prueba", "prueba", "00/00/0000", 1, 123, 1);

        when(libroRepository.save(libro)).thenReturn(libro);

        Libro guardado = libroService.save(libro);

        assertNotNull(guardado);
        assertEquals("prueba", guardado.getTitulo());
    }

    @Test
    public void testDelteteByCodigo(){
        Integer codigo = 1;

        doNothing().when(libroRepository).deleteById((long) codigo);

        libroService.delete(codigo);

        verify(libroRepository, times(1)).deleteById((long) codigo);
    }
}

package com.datalibro.registrolibro.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.datalibro.registrolibro.controller.LibroController;
import com.datalibro.registrolibro.model.Libro;
import com.datalibro.registrolibro.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    private Libro libro;

    @BeforeEach
    void setUp(){
        libro = new Libro();
        libro.setCodigo(1);
        libro.setTitulo("prueba");
        libro.setAutor("prueba");
        libro.setFecha("00/00/0000");
        libro.setIsbn(1);
        libro.setDisponible(1);
    }

    @Test
    public void testGetAllLibros() throws Exception{
        when(libroService.findall()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/v1/libro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].autor").value("prueba"))
                .andExpect(jsonPath("$[0].fecha").value("00/00/0000"))
                .andExpect(jsonPath("$[0].isbn").value(1))
                .andExpect(jsonPath("$[0].disponible").value(1));
    }

    @Test
    public void testGetLibroByCodigo() throws Exception{
        when(libroService.findById(1)).thenReturn(libro);

        mockMvc.perform(get("/api/v1/libro/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(1))
                .andExpect(jsonPath("$.titulo").value("prueba"))
                .andExpect(jsonPath("$.autor").value("prueba"))
                .andExpect(jsonPath("$.fecha").value("00/00/0000"))
                .andExpect(jsonPath("$.isbn").value(1))
                .andExpect(jsonPath("$.disponible").value(1));
    }    

    @Test
    public void testCreateLibro() throws Exception{
        when(libroService.save(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/v1/libro")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].autor").value("prueba"))
                .andExpect(jsonPath("$[0].fecha").value("00/00/0000"))
                .andExpect(jsonPath("$[0].isbn").value(1))
                .andExpect(jsonPath("$[0].disponible").value(1));
    }

    @Test
    public void testUpdateLibro() throws Exception{
        when(libroService.save(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(put("/api/v1/libro/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].autor").value("prueba"))
                .andExpect(jsonPath("$[0].fecha").value("00/00/0000"))
                .andExpect(jsonPath("$[0].isbn").value(1))
                .andExpect(jsonPath("$[0].disponible").value(1));
    }

    @Test
    public void testDelteteLibro() throws Exception{
        doNothing().when(libroService).delete(1);

        mockMvc.perform(delete("/api/v2/libro/1"))
                .andExpect(status().isOk());

        verify(libroService, times(1)).delete(1);
    }

}

package com.datalibro.registrolibrousr.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.datalibro.registrolibrousr.controller.PedidoController;
import com.datalibro.registrolibrousr.model.Pedido;
import com.datalibro.registrolibrousr.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido pedido;

    @BeforeEach
    void setUp(){
        pedido = new Pedido();
        pedido.setCodigo(1);
        pedido.setTitulo("prueba");
        pedido.setNombre("prueba");
        pedido.setRut(123456789);
        pedido.setFechaini("00/00/0000");
        pedido.setFechadevo("00/00/0000");
    }

    @Test
    public void testGetAllPedidos() throws Exception{
        when(pedidoService.findall()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v1/pedido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].nombre").value("prueba"))
                .andExpect(jsonPath("$[0].rut").value(123456789))
                .andExpect(jsonPath("$[0].fechaini").value("00/00/0000"))
                .andExpect(jsonPath("$[0].fechadevo").value("00/00/0000"));
    }

    @Test
    public void testGetPedidoByCodigo() throws Exception{
        when(pedidoService.findById(1)).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].nombre").value("prueba"))
                .andExpect(jsonPath("$[0].rut").value(123456789))
                .andExpect(jsonPath("$[0].fechaini").value("00/00/0000"))
                .andExpect(jsonPath("$[0].fechadevo").value("00/00/0000")); 
    }

    @Test
    public void testCreatePedido() throws Exception{
        when(pedidoService.save(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedido")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].nombre").value("prueba"))
                .andExpect(jsonPath("$[0].rut").value(123456789))
                .andExpect(jsonPath("$[0].fechaini").value("00/00/0000"))
                .andExpect(jsonPath("$[0].fechadevo").value("00/00/0000")); 
    }    

    @Test
    public void testUpdatePedido() throws Exception{
        when(pedidoService.save(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(put("/api/v1/pedido/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].titulo").value("prueba"))
                .andExpect(jsonPath("$[0].nombre").value("prueba"))
                .andExpect(jsonPath("$[0].rut").value(123456789))
                .andExpect(jsonPath("$[0].fechaini").value("00/00/0000"))
                .andExpect(jsonPath("$[0].fechadevo").value("00/00/0000")); 
    }    

    @Test
    public void testDeletePedido() throws Exception{
        doNothing().when(pedidoService).delete(1);

        mockMvc.perform(delete("/api/v1/pedido/1"))
                .andExpect(status().isOk());
        
        verify(pedidoService, times(1)).delete(1);
    }
}

package com.datalibro.registrolibrousr.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.datalibro.registrolibrousr.model.Pedido;
import com.datalibro.registrolibrousr.repository.PedidoRepository;
import com.datalibro.registrolibrousr.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockitoBean
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAll(){
        when(pedidoRepository.findAll()).thenReturn(List.of(new Pedido("prueba", "prueba", "00/00/0000", "00/00/0000", 123456789, 1)));

        List<Pedido> pedidos = pedidoService.findall();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    public void testFindByCodigo(){
        Integer codigo = 1;
        Pedido pedido = new Pedido("prueba", "prueba", "00/00/0000", "00/00/0000", 123456789, 1);

        when(pedidoRepository.findById((long) codigo)).thenReturn(Optional.of(pedido));

        Pedido found = pedidoService.findById(codigo);

        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave(){
        Pedido pedido = new Pedido("prueba", "prueba", "00/00/0000", "00/00/0000", 123456789, 1);

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido guardado = pedidoService.save(pedido);

        assertNotNull(guardado);
        assertEquals("prueba", guardado.getTitulo());
    }

    @Test
    public void testDeleteByCodigo(){
        Integer codigo = 1;

        doNothing().when(pedidoRepository).deleteById((long) codigo);

        pedidoService.delete(codigo);

        verify(pedidoRepository, times(1)).deleteById((long) codigo);
    }
}

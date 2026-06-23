package com.datalibro.registrolibrousr.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.datalibro.registrolibrousr.dto.PedidoDTO;
import com.datalibro.registrolibrousr.model.Pedido;

import java.util.List;

@Service
public class CargaMasivaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void procesarCarga(List<PedidoDTO> listaDto){
        int batchSize = 50;

        for (int i = 0; i < listaDto.size(); i++){
            PedidoDTO dto = listaDto.get(i);

            Pedido pedido = new Pedido();
            pedido.setRut(dto.getRut());
            pedido.setNombre(dto.getNombre());
            pedido.setTitulo(dto.getTitulo());
            pedido.setFechaini(dto.getFechaini());
            pedido.setFechadevo(dto.getFechadevo());
            pedido.setCodigo(dto.getCodigo());

            entityManager.persist(pedido);

            if(i > 0 && i % batchSize == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}

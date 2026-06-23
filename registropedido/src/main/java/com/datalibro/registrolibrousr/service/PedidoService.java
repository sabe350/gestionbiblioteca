package com.datalibro.registrolibrousr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datalibro.registrolibrousr.model.Pedido;
import com.datalibro.registrolibrousr.repository.PedidoRepository;

// import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findall(){
        return repository.findAll();
    }

    public Pedido findById(long codigo){
        return repository.findById(codigo).get();
    }

    public Pedido save(Pedido pedido){
        return repository.save(pedido);
    }

    public void delete(long codigo){
        repository.deleteById(codigo);
    }
}


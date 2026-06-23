package com.datalibro.registrolibrousr.repository;

import org.springframework.stereotype.Repository;

import com.datalibro.registrolibrousr.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.ArrayList;
// import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}


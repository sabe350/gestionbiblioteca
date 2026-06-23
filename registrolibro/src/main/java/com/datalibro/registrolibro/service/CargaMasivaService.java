package com.datalibro.registrolibro.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.datalibro.registrolibro.dto.LibroDTO;
import com.datalibro.registrolibro.model.Libro;

import java.util.List;

@Service
public class CargaMasivaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void procesarCarga(List<LibroDTO> listaDto){
        int batchSize = 50;

        for (int i = 0; i < listaDto.size(); i++){
            LibroDTO dto = listaDto.get(i);

            Libro libro = new Libro();
            libro.setCodigo(dto.getCodigo());
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setFecha(dto.getFecha());
            libro.setIsbn(dto.getIsbn());
            libro.setDisponible(dto.getDisponible());

            entityManager.persist(libro);

            if(i > 0 && i % batchSize == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}

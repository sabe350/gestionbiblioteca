package com.datalibro.registrousuario.service;

import org.springframework.stereotype.Service;

import com.datalibro.registrousuario.dto.UsuarioDTO;
import com.datalibro.registrousuario.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CargaMasivaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void procesarCarga(List<UsuarioDTO> listaDto){
        int batchSize = 50;

        for (int i = 0; i < listaDto.size(); i++){
            UsuarioDTO dto = listaDto.get(i);

            Usuario usuario = new Usuario();
            usuario.setRut(dto.getRut());
            usuario.setNombre(dto.getNombre());
            usuario.setApellido(dto.getApellido());
            usuario.setPassword(dto.getPassword());
            usuario.setEmail(dto.getEmail());

            entityManager.persist(usuario);

            if(i > 0 && i % batchSize == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}

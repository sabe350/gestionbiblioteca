package com.datalibro.registrousuario.service;

import com.datalibro.registrousuario.model.Usuario;
import com.datalibro.registrousuario.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findall(){
        return repository.findAll();
    }

    public Usuario findById(long rut){
        return repository.findById(rut).get();
    }

    public Usuario save(Usuario usuario){
        return repository.save(usuario);
    }

    public void delete(long rut){
        repository.deleteById(rut);
    }
}


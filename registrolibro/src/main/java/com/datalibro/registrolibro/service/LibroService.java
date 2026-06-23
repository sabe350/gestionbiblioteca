package com.datalibro.registrolibro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datalibro.registrolibro.model.Libro;
import com.datalibro.registrolibro.repository.LibroRepository;

// import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository repository;

    public List<Libro> findall(){
        return repository.findAll();
    }

    public Libro findById(long codigo){
        return repository.findById(codigo).get();
    }

    public Libro save(Libro libro){
        return repository.save(libro);
    }

    public void delete(long codigo){
        repository.deleteById(codigo);
    }
}


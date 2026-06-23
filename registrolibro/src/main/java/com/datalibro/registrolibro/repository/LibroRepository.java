package com.datalibro.registrolibro.repository;

import org.springframework.stereotype.Repository;

import com.datalibro.registrolibro.model.Libro;

import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.ArrayList;
// import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    
}


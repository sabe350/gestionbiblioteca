package com.datalibro.registrousuario.repository;

import org.springframework.stereotype.Repository;
import com.datalibro.registrousuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.ArrayList;
// import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}


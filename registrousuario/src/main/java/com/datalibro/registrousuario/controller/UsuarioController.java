package com.datalibro.registrousuario.controller;

//import com.datalibro.registrousuario.service.LibroClient;
import com.datalibro.registrousuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.datalibro.registrousuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v8/usuario") 
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion")
@Tag (name="Registro de usuarios", description = "Operaciones relacionadas con el registor de usuarios al sistema de biblioteca")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    @Operation(summary = "Lista todos los usuarios", description = "Lista todos los usuarios regustrados en la base de datos")
    public ResponseEntity<List<Usuario>> mostrar(){
        List<Usuario> usuarios = service.findall();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Operation(summary = "Agrega usuarios", description = "Agrega usuarios a la base de datos")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    
    @GetMapping(value="/{rut}")
    @Operation(summary = "Buscar usuario", description = "Busca usuario correspondiente al rut ingresado")
    public ResponseEntity<Usuario> search(@PathVariable Integer rut){
        try{
            Usuario usuario = service.findById(rut);
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping(value="/{rut}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos del usuario seleccionado")
    public ResponseEntity<Usuario> upd(@PathVariable Integer rut, @RequestBody Usuario usuario){
        try{
            Usuario usr = service.findById(rut);
            usr.setRut(rut);
            usr.setNombre(usuario.getNombre());
            usr.setApellido(usuario.getApellido());
            usr.setPassword(usuario.getPassword());
            usr.setEmail(usuario.getEmail());
            
            service.save(usr);
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{rut}")
    @Operation(summary = "Eliminar usuario", description = "Elimina usuario correspondiente al rut ingresado")
    public ResponseEntity<?> del(@PathVariable long rut){
        try{
            service.delete(rut);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

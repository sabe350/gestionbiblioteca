package com.datalibro.registrolibro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.datalibro.registrolibro.model.Libro;
import com.datalibro.registrolibro.service.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;





@RestController
@RequestMapping(path="/api/v1/libro")
@SecurityRequirement(name="Ingrese token generado en Inicio de sesion") 
@Tag (name="Registro de libros", description = "Operaciones relacionadas con el registro de libros en al sistema de biblioteca")
public class LibroController {
    @Autowired
    private LibroService service;

    @GetMapping
    @Operation(summary = "Mostrar libro", description = "Muestra todos los libros en base de datos por codigo")
    public ResponseEntity<List<Libro>> mostrar(){
        List<Libro> libros = service.findall();
        if (libros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    @Operation(summary = "Agrega libros", description = "Agrega libros a la base de datos")
    public ResponseEntity<Libro> save(@RequestBody Libro libro){
        Libro nuevoLibro = service.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }
    
    @GetMapping(value="/{codigo}")
    @Operation(summary = "Busca libro", description = "Busca libro correspondiente al codigo ingresado")
    public ResponseEntity<Libro> search(@PathVariable Integer codigo){
        try{
            Libro libro = service.findById(codigo);
            return ResponseEntity.ok(libro);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping(value="/{codigo}")
    @Operation(summary = "Actualiza libro", description = "Actualiza los datos del libro seleccionado")
    public ResponseEntity<Libro> upd(@PathVariable Integer codigo, @RequestBody Libro libro){
        try{
            Libro lbr = service.findById(codigo);
            lbr.setCodigo(codigo);
            lbr.setTitulo(libro.getTitulo());
            lbr.setAutor(libro.getAutor());
            lbr.setFecha(libro.getFecha());
            lbr.setIsbn(libro.getIsbn());
            lbr.setDisponible(libro.getDisponible());
            
            service.save(lbr);
            return ResponseEntity.ok(libro);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{codigo}")
    @Operation(summary = "Eliminar libro", description = "Elimina libro correspondiente al rut ingresado")
    public ResponseEntity<?> del(@PathVariable long codigo){
        try{
            service.delete(codigo);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

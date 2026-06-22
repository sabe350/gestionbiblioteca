package com.datalibro.registrousuario;

import com.datalibro.registrousuario.model.*;
import com.datalibro.registrousuario.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            Usuario testusuario = new Usuario();
            testusuario.setRut(faker.number().numberBetween(100000000,999999999));
            testusuario.setNombre(faker.name().firstName());  
            testusuario.setApellido(faker.name().lastName()); 
            testusuario.setPassword(faker.code().asin());
            testusuario.setEmail(faker.internet().emailAddress());
            usuarioRepository.save(testusuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();
    }

}

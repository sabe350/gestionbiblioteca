package com.datalibro.registrolibro;

import com.datalibro.registrolibro.model.*;
import com.datalibro.registrolibro.repository.*;
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
    private LibroRepository libroRepository;

    @Override
    public void run(String... arg) throws Exception{
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            Libro testLibro = new Libro();
            testLibro.setCodigo(faker.number().numberBetween(10000, 99999));
            testLibro.setTitulo(faker.book().title());
            testLibro.setAutor(faker.name().fullName());
            testLibro.setFecha(faker.timeAndDate().past().toString());
            testLibro.setIsbn(faker.number().numberBetween(1000, 9999));
            testLibro.setDisponible(faker.number().numberBetween(0, 2));
            libroRepository.save(testLibro);

        }

        List<Libro> libros = libroRepository.findAll();
    }

}

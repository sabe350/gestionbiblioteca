package com.datalibro.registrolibrousr;

import com.datalibro.registrolibrousr.model.*;
import com.datalibro.registrolibrousr.repository.*;
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
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            Pedido testPedido = new Pedido();
            testPedido.setRut(faker.number().numberBetween(100000000,999999999));
            testPedido.setNombre(faker.name().firstName());
            testPedido.setTitulo(faker.book().title());
            testPedido.setCodigo(faker.number().numberBetween(10000, 99999));
            testPedido.setFechaini(faker.timeAndDate().past().toString());
            testPedido.setFechadevo(faker.timeAndDate().future().toString());;
            pedidoRepository.save(testPedido);
        }

        List<Pedido> pedidos = pedidoRepository.findAll();
    }

}

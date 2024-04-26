package com.fernandoschimidt.pontointeresse.config;

import com.fernandoschimidt.pontointeresse.entity.Point;
import com.fernandoschimidt.pontointeresse.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private PointRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Point(null, "Lanchonete", 27, 12));
        repository.save(new Point(null, "Posto", 31, 18));
        repository.save(new Point(null, "Joalheria", 15, 12));
        repository.save(new Point(null, "Floricultura", 19, 21));
        repository.save(new Point(null, "Pub", 12, 8));
        repository.save(new Point(null, "Supermercado", 23, 6));
        repository.save(new Point(null, "Churrascaria", 28, 2));
    }
}

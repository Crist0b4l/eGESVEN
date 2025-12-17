package com.egesven.backend.config;

import com.egesven.backend.model.Producto;
import com.egesven.backend.model.Usuario;
import com.egesven.backend.repository.ProductoRepository;
import com.egesven.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ProductoRepository prodRepo, UsuarioRepository userRepo) {
        return args -> {
            // Cargar Productos
            if (prodRepo.count() == 0) {
                prodRepo.save(new Producto("Notebook Gamer HP", "Intel i7, 16GB RAM", 990000.0, 10, "https://m.media-amazon.com/images/I/71hIfcIPyxL._AC_SX679_.jpg"));
                prodRepo.save(new Producto("Mouse Logi G203", "Luces RGB", 25000.0, 50, "https://m.media-amazon.com/images/I/61UxfXTuyvL._AC_SX679_.jpg"));
                prodRepo.save(new Producto("Teclado Mecánico", "Switch Red", 45000.0, 20, "https://m.media-amazon.com/images/I/71N4c0cXkOL._AC_SX679_.jpg"));
                System.out.println(">>> Productos cargados <<<");
            }

            // Cargar Usuario de Prueba
            if (userRepo.count() == 0) {
                userRepo.save(new Usuario("admin", "1234", "Cristóbal Varas"));
                System.out.println(">>> Usuario Admin creado (User: admin, Pass: 1234) <<<");
            }
        };
    }
}
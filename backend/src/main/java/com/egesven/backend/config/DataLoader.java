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
            // Cargar Productos solo si está vacío
            if (prodRepo.count() == 0) {
                // Producto 1: Notebook Bestia
                prodRepo.save(new Producto(
                        "ASUS ROG Strix Scar 18",
                        "Intel Core Ultra 9, 64GB RAM, RTX 5090 (24GB), 2TB SSD",
                        4849990.0,
                        5,
                        "https://media.solotodo.com/media/products/2074259_picture_1746717732.png"
                ));

                // Producto 2: Mouse
                prodRepo.save(new Producto(
                        "Corsair NightSabre Wireless",
                        "RGB, 26000 DPI, 11 Botones programables, Bluetooth/USB",
                        199990.0,
                        15,
                        "https://media.solotodo.com/media/products/1893543_picture_1710356740.webp"
                ));

                // Producto 3: Teclado
                prodRepo.save(new Producto(
                        "Corsair K100 RGB",
                        "Mecánico Cherry MX Speed, Full Size, Rueda de control",
                        349990.0,
                        10,
                        "https://media.solotodo.com/media/products/1342885_picture_1615472637.jpg"
                ));

                System.out.println(">>> Productos ASUS y Corsair cargados <<<");
            }

            // Cargar Usuario Admin
            if (userRepo.count() == 0) {
                userRepo.save(new Usuario("admin", "1234", "Usuario Admin"));
                System.out.println(">>> Usuario Admin creado <<<");
            }
        };
    }
}
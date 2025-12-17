package com.egesven.backend.controller;

import com.egesven.backend.model.Producto;
import com.egesven.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // ¡Importante! Permite que cualquiera (tu frontend) pida datos
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    // 1. Endpoint para obtener TODOS los productos (Para el Catálogo)
    // URL: http://localhost:8080/api/productos
    @GetMapping
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    // 2. Endpoint para obtener UN producto por ID (Para ver el detalle)
    // URL: http://localhost:8080/api/productos/1
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}
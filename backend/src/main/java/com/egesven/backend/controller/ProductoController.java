package com.egesven.backend.controller;

import com.egesven.backend.model.Producto;
import com.egesven.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}
package com.egesven.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;
    private Double totalCompra;
    private String detalleProductos; // Guardaremos "Mouse, Teclado" como texto simple para no complicarnos
    private LocalDateTime fecha;

    public Pedido() {}

    public Pedido(String nombreCliente, Double totalCompra, String detalleProductos) {
        this.nombreCliente = nombreCliente;
        this.totalCompra = totalCompra;
        this.detalleProductos = detalleProductos;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Double getTotalCompra() { return totalCompra; }
    public void setTotalCompra(Double totalCompra) { this.totalCompra = totalCompra; }
    public String getDetalleProductos() { return detalleProductos; }
    public void setDetalleProductos(String detalleProductos) { this.detalleProductos = detalleProductos; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
package com.egesven.backend.controller;

import com.egesven.backend.model.Pedido;
import com.egesven.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/crear")
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        // Aquí le ponemos la fecha actual automática
        pedido.setFecha(java.time.LocalDateTime.now());
        System.out.println(">>> Pedido recibido de: " + pedido.getNombreCliente());
        return pedidoRepository.save(pedido);
    }
}
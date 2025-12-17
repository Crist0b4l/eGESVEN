package com.egesven.backend.repository;

import com.egesven.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // ¡Listo! Solo con esto ya tienes guardar, borrar, buscarPorId y listarTodo.
    // Es la magia de Spring Data JPA.
}
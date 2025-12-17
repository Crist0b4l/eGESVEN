package com.egesven.backend.repository;

import com.egesven.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico: Spring crea la consulta SQL solo leyendo el nombre del método
    Usuario findByUsernameAndPassword(String username, String password);
}
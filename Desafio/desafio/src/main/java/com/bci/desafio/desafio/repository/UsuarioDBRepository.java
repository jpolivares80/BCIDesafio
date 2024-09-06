package com.bci.desafio.desafio.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.bci.desafio.desafio.models.Usuario;
import com.bci.desafio.desafio.models.UsuarioDB;

@Repository
public interface UsuarioDBRepository extends JpaRepository<UsuarioDB, UUID> {
  // Método para buscar un usuario por correo electrónico
  Optional<UsuarioDB> findByEmail(String email);
    
  // Método para verificar si un correo existe
  boolean existsByEmail(String email);
}

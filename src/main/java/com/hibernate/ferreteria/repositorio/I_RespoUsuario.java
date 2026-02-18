package com.hibernate.ferreteria.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.ferreteria.entity.Usuario;

public interface I_RespoUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
}

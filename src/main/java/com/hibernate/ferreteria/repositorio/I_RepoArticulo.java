package com.hibernate.ferreteria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.ferreteria.entity.Articulo;

//Con JpaRepository se crean los metodos basicos para el CRUD
public interface I_RepoArticulo extends JpaRepository<Articulo, Long>{
    
}

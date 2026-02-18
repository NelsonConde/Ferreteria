package com.hibernate.ferreteria.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.ferreteria.DTOs.ArticuloDTO;
import com.hibernate.ferreteria.entity.Articulo;
import com.hibernate.ferreteria.mapper.ArticuloMapper;
import com.hibernate.ferreteria.repositorio.I_RepoArticulo;

@Service
public class ArticuloService {

    @Autowired
    private I_RepoArticulo repo;

    public List<ArticuloDTO> servConsulta() {
        //Como el repositorio solo devuelve Articulo, usamos el mapper para convertir a DTO con .map y collect para volver a armar la lista
        return repo.findAll().stream().map(ArticuloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ArticuloDTO servBuscarPorId(Long id) {
        Optional<Articulo> articulo = repo.findById(id);
        if (articulo.isPresent()) {
            return ArticuloMapper.toDTO(articulo.get());
        } else {
            throw new RuntimeException("Articulo no encontrado con id: " + id);
        }
    }
    public ArticuloDTO servInsertar(ArticuloDTO dto) {
        Articulo articulo = ArticuloMapper.toEntity(dto);
        // Esto es una buena practica para saber si realmente se inserto o no
        Articulo insertado = repo.save(articulo);
        return ArticuloMapper.toDTO(insertado);
    }

    public ArticuloDTO servActualizar(Long id, ArticuloDTO dto) {
        Optional<Articulo> articuloExistente = repo.findById(id);
        if (articuloExistente.isPresent()) {
            Articulo articulo = articuloExistente.get();
            //Aqui se actualiza con el objeto que entro
            articulo.setNombre(dto.getNombre());
            articulo.setPrecio(dto.getPrecio());
            articulo.setExistencia(dto.getExistencia());
            //Aqui se guarda 
            Articulo actualizado = repo.save(articulo);
            return ArticuloMapper.toDTO(actualizado);
        } else {
            throw new RuntimeException("Articulo no encontrado con id: " + id);
        }
    }

    public String servEliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Articulo eliminado correctamente";
        }
        else {
            return "Articulo " + id + " no encontrado";
        }
    }
}

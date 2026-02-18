package com.hibernate.ferreteria.mapper;

import com.hibernate.ferreteria.DTOs.ArticuloDTO;
import com.hibernate.ferreteria.entity.Articulo;

//Esta clase utilitaria que ayuda a hacer la conversion entre la entidad Articulo y el DTO ArticuloDTO
public class ArticuloMapper {

    //Para sacar el DTO desde la entidad
    public static ArticuloDTO toDTO(Articulo articulo) {
        return new ArticuloDTO(
                articulo.getId(),
                articulo.getNombre(),
                articulo.getPrecio(),
                articulo.getExistencia()
        );
    }

    //Para sacar la entidad desde el DTO
    public static Articulo toEntity(ArticuloDTO articuloDTO) {
        Articulo articulo = new Articulo();
        articulo.setId(articuloDTO.getId());
        articulo.setNombre(articuloDTO.getNombre());
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setExistencia(articuloDTO.getExistencia());
        return articulo;
    }
    
}


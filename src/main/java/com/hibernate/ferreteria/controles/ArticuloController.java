package com.hibernate.ferreteria.controles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.ferreteria.DTOs.ArticuloDTO;
import com.hibernate.ferreteria.mapper.ArticuloMapper;
import com.hibernate.ferreteria.repositorio.I_RepoArticulo;
import com.hibernate.ferreteria.servicios.ArticuloService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


//Permite configurar el controlador para aceptar solicitudes de diferentes orígenes
//El * indica que se permiten solicitudes de cualquier origen
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService servicio;

    //Muestra todos los articulos
    @GetMapping
    public List<ArticuloDTO> consulta() {
        return servicio.servConsulta();
    }

    //Buscando por id
    @GetMapping("/{id}")
    public ArticuloDTO buscarPorId(@PathVariable Long id) {
        return servicio.servBuscarPorId(id);
    }

    //Inserta datos en la bd
    @PostMapping
    public ArticuloDTO insertarArticulo(@RequestBody ArticuloDTO dto) {
        return servicio.servInsertar(dto);
    }
    
    //Actualiza datos en la bd
    @PutMapping("/{id}")
    public ArticuloDTO actualizArticulo(@PathVariable Long id, @RequestBody ArticuloDTO dto) {
        return servicio.servActualizar(id, dto);
    }

    //Elimina un articulo
    @DeleteMapping("/{id}")
    public String eliminarArticulo(@PathVariable Long id) {
        return servicio.servEliminar(id);
    }
}

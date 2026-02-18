package com.hibernate.ferreteria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Convierte la clase en una tabla en la bd
//Esto conecta con la bd no crea nada, si no hay bd o tablas salta error
@Entity
@Table(name = "articulos")
//Genera getters y setters automaticamente
@Getter
@Setter
//Genera constructor vacio
@NoArgsConstructor
//Genera constructor con todos los parametros
@AllArgsConstructor
public class Articulo {

    //Marca la llave primaria
    @Id
    //Genera los valores automaticamente (No hay que pasar id como parametro)
    //Pero esto tambien se debe configurar en la bd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Mapear los atributos de la clase con las columnas de la tabla (json por ejemplo para mostrar en el navegador)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private double precio;

    @Column(name = "existencia")
    private int existencia;

    //Para que imprima los datos de la bd en consola
    @Override
    public String toString() {
        return "Articulo [id=" + id + 
                ", nombre=" + nombre + 
                ", precio=" + precio + 
                ", existencia=" + existencia + "]";
    }

}

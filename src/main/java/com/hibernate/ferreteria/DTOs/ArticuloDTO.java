package com.hibernate.ferreteria.DTOs;

//En un DTO no se usan anotaciones de JPA porque no es una entidad de base de datos
public class ArticuloDTO {
    private Long id;
    private String nombre;
    private double precio;
    private int existencia;

    public ArticuloDTO(Long id, String nombre, double precio, int existencia) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return "ArticuloDTO [id=" + id + 
                ", nombre=" + nombre + 
                ", precio=" + precio + 
                ", existencia=" + existencia + 
                "]";
    }   
}

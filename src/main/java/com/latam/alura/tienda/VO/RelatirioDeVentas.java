package com.latam.alura.tienda.VO;

import java.time.LocalDate;

public class RelatirioDeVentas {

    /*
    La clase DTO debe contener un constructor compatible con consultas JPQL.

    JPA crea instancias de la clase DTO a través de un constructor que recibe parámetros, según la consulta JPQL.
     */



    private String nombre;
    private Long CantidadDeProductos;
    private LocalDate FechaDeUltimaVenta;


    public RelatirioDeVentas(String nombre, Long cantidadDeProductos, LocalDate fechaDeUltimaVenta) {
        this.nombre = nombre;
        CantidadDeProductos = cantidadDeProductos;
        FechaDeUltimaVenta = fechaDeUltimaVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCantidadDeProductos() {
        return CantidadDeProductos;
    }

    public void setCantidadDeProductos(Long cantidadDeProductos) {
        CantidadDeProductos = cantidadDeProductos;
    }

    public LocalDate getFechaDeUltimaVenta() {
        return FechaDeUltimaVenta;
    }

    public void setFechaDeUltimaVenta(LocalDate fechaDeUltimaVenta) {
        FechaDeUltimaVenta = fechaDeUltimaVenta;
    }

    @Override
    public String toString() {
        return "RelatirioDeVentas{" +
                "nombre='" + nombre + '\'' +
                ", CantidadDeProductos=" + CantidadDeProductos +
                ", FechaDeUltimaVenta=" + FechaDeUltimaVenta +
                '}';
    }
}

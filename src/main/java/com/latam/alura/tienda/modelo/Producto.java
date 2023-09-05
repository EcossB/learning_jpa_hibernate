package com.latam.alura.tienda.modelo;

import jdk.jfr.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos") //indicandole el nombre de la db.
@NamedQuery(name = "Producto.consultaDePrecio", query = "Select p.precio from Producto as p where p.nombre=:nombre")
public class Producto { //JPA entiende que el nombre de la clase es el mismo que el de la tabla, en este caso no es asi.
    //mapeamento de nuestra tabla de base datos

    @Id // indicando que es un identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indicado que el valor no sera escrito por el usuario. es identity para h2
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private LocalDate fechaDeRegistro = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY  ) //asi realizamos la relacion entre producto y categoria. muchos productos tiene una categoria Many to one.
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}

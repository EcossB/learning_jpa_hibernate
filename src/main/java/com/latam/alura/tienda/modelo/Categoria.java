package com.latam.alura.tienda.modelo;

/*
* Tenemos categorias limitadas.
* cuando tenemos elementos limitados y conecidos es bueno crear un enumerador.
* */

import javax.persistence.*;

@Entity
@Table(name = "Categorias")
public class Categoria {

    /*
    SOFTWARES, //esto basicamente se guarda como un arreglo, esto basicamente seria un 1
    LIBROS,
    CELULARES
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Categoria() {
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

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}

package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAutils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class RegistroDeProducto {
    public static void main(String[] args) {
        /*
        instanciando un producto

        Producto celular = new Producto();
        celular.setNombre("Samsung");
        celular.setDescripcion("Samsung usado");
        celular.setPrecio(new BigDecimal("1000"));
         */

        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Samsung", "Samsung S20 Usado",
                new BigDecimal("10000"), celulares);

        /*
        encargado de realizar las operaciones de conexion con la db
        Guardar,selecionar,borrar,actulizar. etc etc

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda");
        En esta línea de código, se crea una instancia de EntityManagerFactory. EntityManagerFactory es una factoría para instancias de EntityManager, y se utiliza para configurar y crear instancias de EntityManager.
        persistence.createEntityManagerFactory("tienda") crea un EntityManagerFactory con el nombre "tienda". El nombre "tienda" generalmente se refiere a una unidad de persistencia definida en el archivo de configuración de JPA (como persistence.xml) que contiene información sobre cómo conectarse a la base de datos y cómo mapear las entidades de la base de datos a objetos Java.

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda"); //clase que utiliza el entity manager
        EntityManager em = factory.createEntityManager(); //este instanciara una clase de tipo entity

         */

        EntityManager em = JPAutils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

// ciclo de vida de JPA
        em.getTransaction().begin(); // indicando que las trasaciones van a comenzar.
        //em.persist(celular);
        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);
        em.getTransaction().commit(); //envia los valores de la instancia a la DB.
        em.close(); // se cierra las trasaciones.


    }
}

package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductoDao {

    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    //metodo guardar
    public void guardar(Producto producto){
        this.em.persist(producto);
    }

    public Producto consultarConID(Long id){
        return this.em.find(Producto.class, id);
    }

    public List<Producto> consultarTodo(){
        //utilizando jpql. Java persistence query language

        String jqpl = "SELECT P FROM Producto AS P";
        return em.createQuery(jqpl, Producto.class).getResultList();
    }

    public List<Producto> consultarPorNombre(String nombre){
        String jpql = "Select P from Producto as P where P.nombre=:nombre ";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();

    }

    public List<Producto> consultaPorNombreDeCategoria(String nombre){
        String jpql = "Select P from Producto As P where P.categoria.nombre=:nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre",nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre){
        String jpql = "Select p.precio from Producto as p where p.nombre=:nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}

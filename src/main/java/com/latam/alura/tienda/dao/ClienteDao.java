package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Clientes;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    //metodo guardar
    public void guardar(Clientes Clientes){
        this.em.persist(Clientes);
    }

    public Clientes consultarConID(Long id){
        return this.em.find(Clientes.class, id);
    }

    public List<Clientes> consultarTodo(){
        //utilizando jpql. Java persistence query language

        String jqpl = "SELECT P FROM Clientes AS P";
        return em.createQuery(jqpl, Clientes.class).getResultList();
    }

    public List<Clientes> consultarPorNombre(String nombre){
        String jpql = "Select P from Clientes as P where P.nombre=:nombre ";
        return em.createQuery(jpql, Clientes.class).setParameter("nombre", nombre).getResultList();

    }

    public List<Clientes> consultaPorNombreDeCategoria(String nombre){
        String jpql = "Select P from Clientes As P where P.categoria.nombre=:nombre";
        return em.createQuery(jpql, Clientes.class).setParameter("nombre",nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeClientes(String nombre){
        String jpql = "Select p.precio from Clientes as p where p.nombre=:nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}

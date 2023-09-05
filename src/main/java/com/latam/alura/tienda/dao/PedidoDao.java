package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.VO.RelatirioDeVentas;
import com.latam.alura.tienda.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    //metodo guardar
    public void guardar(Pedido Pedido){
        this.em.persist(Pedido);
    }

    public Pedido consultarConID(Long id){
        return this.em.find(Pedido.class, id);
    }

    public List<Pedido> consultarTodo(){
        //utilizando jpql. Java persistence query language

        String jqpl = "SELECT P FROM Pedido AS P";
        return em.createQuery(jqpl, Pedido.class).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDePedido(String nombre){
        String jpql = "Select p.precio from Pedido as p where p.nombre=:nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public BigDecimal valorTotalVendido(){
        String  jpql = "Select MAX(p.valor_total) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public double valorPromedioVendido(){
        String jpql = "Select AVG(p.valor_total) FROM Pedido p";
        return em.createQuery(jpql, Double.class).getSingleResult();
    }

    public List<Object[]> relatorioDeVentas(){
        String jpql = "SELECT producto.nombre, " +
                "SUM(item.cantidad), " +
                "MAX(pedido.fecha) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY SUM(item.cantidad) DESC";

        return em.createQuery(jpql,Object[].class).getResultList();
    }

    //haciendo lo mismo que antes pero con un VO= value object
    // aqui utilizamos tambien cosas de jpa como la palabra reservada new
    public List<RelatirioDeVentas> relatorioDeVentasVO(){
        String jpql = "SELECT new com.latam.alura.tienda.VO.RelatirioDeVentas(producto.nombre, " +
                "SUM(item.cantidad), " +
                "MAX(pedido.fecha)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY SUM(item.cantidad) DESC";

        return em.createQuery(jpql,RelatirioDeVentas.class).getResultList();
    }
}

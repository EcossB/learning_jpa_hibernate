package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.VO.RelatirioDeVentas;
import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.*;
import com.latam.alura.tienda.utils.JPAutils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDePedido {
    public static void main(String[] args) {
        registrarProducto();

        EntityManager em = JPAutils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);

        Producto producto = productoDao.consultarConID(1l);
        PedidoDao pedidoDao = new PedidoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Clientes clientes =  new Clientes("juan", "40233491");

        Pedido pedido = new Pedido(clientes);

        pedido.agregarItems(new ItemsPedido(8, producto, pedido));

        em.getTransaction().begin();
        clienteDao.guardar(clientes);
        pedidoDao.guardar(pedido);
        em.getTransaction().commit();

        BigDecimal valorTotal =  pedidoDao.valorTotalVendido();
        System.out.println(valorTotal);

        List<RelatirioDeVentas> relatorio = pedidoDao.relatorioDeVentasVO();

//        for (Object[] obj:relatorio) {
//            System.out.println(obj[0]);
//            System.out.println(obj[1]);
//            System.out.println(obj[2]);
//        }

        relatorio.forEach(System.out::println);
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Iphone", "Nuevo",
                new BigDecimal(1000), celulares);

        EntityManager em = JPAutils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }

}

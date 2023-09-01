package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAutils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroProductosListar {
    public static void main(String[] args) {
        registrarProducto();
        EntityManager em = JPAutils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        Producto producto = productoDao.consultarConID(1l);
        System.out.println(producto.getNombre());

        BigDecimal productos = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi");
        //productos.forEach(prod -> System.out.println(prod.getDescripcion()));
        System.out.println(productos);
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Xiaomi", "Excelente",
                new BigDecimal(800), celulares);

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

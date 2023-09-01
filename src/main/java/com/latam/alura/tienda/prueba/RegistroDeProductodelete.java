package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.utils.JPAutils;

import javax.persistence.EntityManager;

public class RegistroDeProductodelete {
    public static void main(String[] args) {
        /*
        instanciando un producto

        Producto celular = new Producto();
        celular.setNombre("Samsung");
        celular.setDescripcion("Samsung usado");
        celular.setPrecio(new BigDecimal("1000"));
         */

        Categoria celulares = new Categoria("CELULARES");
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
        CategoriaDao categoriaDao = new CategoriaDao(em);

        // ciclo de vida de JPA
        //borrando registros.
        em.getTransaction().begin(); // indicando que las trasaciones van a comenzar.

        categoriaDao.guardar(celulares);

        celulares.setNombre("LIBROS");
        em.flush(); //envia los valores de la instancia a la DB pero si ocurre un error hacer un rollback.
        em.clear(); // esto es para volver la instancia detached

        celulares = em.merge(celulares);//hay que agregar un constructor vacio para que pueda hacer el merge
        celulares.setNombre("SOFTWARES"); //asi se actualiza un registro
        em.flush(); //siempre que se haga una actualizacion debemos de usar un flush o commit para que se manden los datos .


        em.clear(); // volviendo la instancia en detached.
        celulares = em.merge(celulares); // me devuelve el registro de manera managed y asi puedo eliminarlo
        em.remove(celulares); // utilizamos remove para eliminar un registro
        em.flush(); //mandamos los cambios a la db
    }
}

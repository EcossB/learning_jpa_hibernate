package com.latam.alura.tienda.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutils {

    private static EntityManagerFactory em = Persistence.createEntityManagerFactory("tienda");

    public static EntityManager getEntityManager(){
        return em.createEntityManager();
    }

}

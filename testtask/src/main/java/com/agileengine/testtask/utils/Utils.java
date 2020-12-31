package com.agileengine.testtask.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("account.persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}

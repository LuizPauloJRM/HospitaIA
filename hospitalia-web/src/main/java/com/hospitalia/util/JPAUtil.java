package com.hospitalia.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hospitaliaPU");

    @Produces
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}

package com.hospitalia.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAProducer {

    private EntityManagerFactory emf;

    public JPAProducer() {
        // Nome do persistence-unit do persistence.xml
        emf = Persistence.createEntityManagerFactory("hospitaliaPU");
    }

    @Produces
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}

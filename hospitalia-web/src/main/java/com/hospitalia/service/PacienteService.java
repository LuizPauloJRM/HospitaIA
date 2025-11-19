package com.hospitalia.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.hospitalia.model.Paciente;
import com.hospitalia.util.JPAUtil;

@Named
@RequestScoped
public class PacienteService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Paciente paciente) {
    	 try {
             em.getTransaction().begin();

             if (paciente.getId() == null) {
                 em.persist(paciente);
             } else {
                 em.merge(paciente);
             }

             em.getTransaction().commit();
         } finally {
             em.close();
         }
    }

    public Paciente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public List<Paciente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em
                    .createQuery("SELECT p FROM Paciente p ORDER BY p.nome", Paciente.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            Paciente paciente = em.find(Paciente.class, id);

            if (paciente != null) {
                em.getTransaction().begin();
                em.remove(paciente);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}

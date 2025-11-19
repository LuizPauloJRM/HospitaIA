package com.hospitalia.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.hospitalia.model.Medico;

@RequestScoped
public class MedicoService {

   @PersistenceContext
    private EntityManager em;

    public List<Medico> listarTodos() {
        return em.createQuery("SELECT m FROM Medico m", Medico.class)
                 .getResultList();
    }

    @Transactional
    public void salvar(Medico medico) {
        if (medico.getId() == null) {
            em.persist(medico);
        } else {
            em.merge(medico);
        }
    }

    @Transactional
    public void remover(Long id) {
        Medico medico = em.find(Medico.class, id);
        if (medico != null) {
            em.remove(medico);
        }
    }
}
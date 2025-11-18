package com.hospitalia.service;

import com.hospitalia.model.Medico;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class MedicoService {

    @Inject
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

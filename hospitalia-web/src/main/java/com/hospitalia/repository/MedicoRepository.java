package com.hospitalia.repository;

import com.hospitalia.model.Medico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MedicoRepository {

    @PersistenceContext
    private EntityManager em;

    public Medico salvar(Medico medico) {
        return em.merge(medico);
    }

    public void remover(Long id) {
        Medico medico = em.find(Medico.class, id);
        if (medico != null) {
            em.remove(medico);
        }
    }

    public Medico buscarPorId(Long id) {
        return em.find(Medico.class, id);
    }

    public List<Medico> listarTodos() {
        return em.createQuery("from Medico", Medico.class).getResultList();
    }
}

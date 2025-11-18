package com.hospitalia.repository;

import com.hospitalia.model.Atendimento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AtendimentoRepository {

    @PersistenceContext
    private EntityManager em;

    public Atendimento salvar(Atendimento atendimento) {
        return em.merge(atendimento);
    }

    public void remover(Long id) {
        Atendimento atendimento = em.find(Atendimento.class, id);
        if (atendimento != null) {
            em.remove(atendimento);
        }
    }

    public Atendimento buscarPorId(Long id) {
        return em.find(Atendimento.class, id);
    }

    public List<Atendimento> listarTodos() {
        return em.createQuery("from Atendimento", Atendimento.class).getResultList();
    }
}

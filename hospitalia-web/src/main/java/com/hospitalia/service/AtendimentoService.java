package com.hospitalia.service;

import com.hospitalia.model.Atendimento;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class AtendimentoService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Atendimento atendimento) {
        if (atendimento.getId() == null) {
            em.persist(atendimento);
        } else {
            em.merge(atendimento);
        }
    }

    public Atendimento buscarPorId(Long id) {
        return em.find(Atendimento.class, id);
    }

    public List<Atendimento> listarTodos() {
        return em
                .createQuery("SELECT a FROM Atendimento a ORDER BY a.data DESC", Atendimento.class)
                .getResultList();
    }

    @Transactional
    public void remover(Long id) {
        Atendimento atendimento = em.find(Atendimento.class, id);
        if (atendimento != null) {
            em.remove(atendimento);
        }
    }
}

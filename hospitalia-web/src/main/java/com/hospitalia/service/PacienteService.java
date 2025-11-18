package com.hospitalia.service;

import com.hospitalia.model.Paciente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class PacienteService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Paciente paciente) {
        if (paciente.getId() == null) {
            em.persist(paciente);
        } else {
            em.merge(paciente);
        }
    }

    public Paciente buscarPorId(Long id) {
        return em.find(Paciente.class, id);
    }

    public List<Paciente> listarTodos() {
        return em
                .createQuery("SELECT p FROM Paciente p ORDER BY p.nome", Paciente.class)
                .getResultList();
    }

    @Transactional
    public void remover(Long id) {
        Paciente paciente = em.find(Paciente.class, id);
        if (paciente != null) {
            em.remove(paciente);
        }
    }
}

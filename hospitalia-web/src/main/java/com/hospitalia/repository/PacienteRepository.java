package com.hospitalia.repository;

import com.hospitalia.model.Paciente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PacienteRepository {

    @PersistenceContext
    private EntityManager em;

    public Paciente salvar(Paciente paciente) {
        return em.merge(paciente);
    }

    public void remover(Long id) {
        Paciente paciente = em.find(Paciente.class, id);
        if (paciente != null) {
            em.remove(paciente);
        }
    }

    public Paciente buscarPorId(Long id) {
        return em.find(Paciente.class, id);
    }

    public List<Paciente> listarTodos() {
        return em.createQuery("from Paciente", Paciente.class).getResultList();
    }
}
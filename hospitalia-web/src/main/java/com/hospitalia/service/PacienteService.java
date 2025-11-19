package com.hospitalia.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.hospitalia.model.Paciente;

@Named
@RequestScoped
public class PacienteService {

    @Inject
    private EntityManager em;

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
        return em.createQuery("SELECT p FROM Paciente p ORDER BY p.nome", Paciente.class)
                 .getResultList();
    }

    public void remover(Long id) {
        Paciente p = em.find(Paciente.class, id);
        if (p != null) {
            em.remove(p);
        }
    }
}

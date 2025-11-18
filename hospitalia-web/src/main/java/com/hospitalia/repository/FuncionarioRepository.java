package com.hospitalia.repository;

import com.hospitalia.model.Funcionario;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Repositório responsável pelo acesso a dados da entidade Funcionario.
 * Utiliza JPA/Hibernate para realizar operações de persistência.
 */
@ApplicationScoped
public class FuncionarioRepository {

    @PersistenceContext(unitName = "hospitaliaPU")
    private EntityManager em;

    /**
     * Persiste ou atualiza um funcionário.
     */
    @Transactional
    public void salvar(Funcionario funcionario) {
        if (funcionario.getId() == null) {
            em.persist(funcionario);
        } else {
            em.merge(funcionario);
        }
    }

    /**
     * Remove um funcionário pelo ID.
     */
    @Transactional
    public void remover(Long id) {
        Funcionario funcionario = em.find(Funcionario.class, id);
        if (funcionario != null) {
            em.remove(funcionario);
        }
    }

    /**
     * Lista todos os funcionários ordenados por nome.
     */
    public List<Funcionario> listarTodos() {
        TypedQuery<Funcionario> query =
                em.createQuery("SELECT f FROM Funcionario f ORDER BY f.nome", Funcionario.class);
        return query.getResultList();
    }

    /**
     * Busca um funcionário pelo ID.
     */
    public Funcionario buscarPorId(Long id) {
        return em.find(Funcionario.class, id);
    }

    /**
     * Busca funcionário pelo CPF.
     */
    public Funcionario buscarPorCpf(String cpf) {
        try {
            TypedQuery<Funcionario> q =
                    em.createQuery("SELECT f FROM Funcionario f WHERE f.cpf = :cpf", Funcionario.class);
            q.setParameter("cpf", cpf);
            return q.getSingleResult();
        } catch (Exception e) {
            return null; // CPF não encontrado
        }
    }
}

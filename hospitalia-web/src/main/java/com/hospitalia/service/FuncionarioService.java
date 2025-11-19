package com.hospitalia.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.hospitalia.model.Funcionario;
import com.hospitalia.repository.FuncionarioRepository;

/**
 * Camada de serviço responsável por aplicar regras de negócio
 * relacionadas aos funcionários. Aqui também controlamos
 * transações e operações que envolvem validações.
 */
@ApplicationScoped
public class FuncionarioService {

    @Inject
    private FuncionarioRepository funcionarioRepository;

    @PersistenceContext(unitName = "hospitaliaPU")
    private EntityManager em;
    
    /**
     * Salva ou atualiza um funcionário no banco de dados.
     * Caso o CPF já exista, a operação pode ser bloqueada.
     */
    @Transactional
    public void salvar(Funcionario funcionario) {

        // Regra: CPF duplicado não é permitido
        Funcionario existente = funcionarioRepository.buscarPorCpf(funcionario.getCpf());

        if (existente != null && !existente.getId().equals(funcionario.getId())) {
            throw new RuntimeException("Já existe um funcionário cadastrado com esse CPF.");
        }

        funcionarioRepository.salvar(funcionario);
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
     * Lista todos os funcionários cadastrados.
     */
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.listarTodos();
    }

    /**
     * Busca um funcionário pelo ID.
     */
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.buscarPorId(id);
    }
}
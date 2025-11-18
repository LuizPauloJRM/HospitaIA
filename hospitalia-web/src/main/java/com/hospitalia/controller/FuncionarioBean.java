package com.hospitalia.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hospitalia.model.Funcionario;
import com.hospitalia.service.FuncionarioService;

/**
 * Controlador responsável pela interação entre a camada de visão (JSF)
 * e a camada de serviço referente à entidade Funcionario.
 * 
 * Este Managed Bean gerencia operações de CRUD, mantém o estado da tela
 * enquanto ela estiver aberta e trabalha com injeção de dependência.
 */
@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Entidade sendo manipulada no formulário
    private Funcionario funcionario;

    // Lista usada para preenchimento da tabela na tela
    private List<Funcionario> funcionarios;

    // Classe de serviço que contém as regras de negócio
    @Inject
    private FuncionarioService funcionarioService;

    /**
     * Método executado automaticamente após a criação do bean.
     * Inicializa a entidade e carrega a lista inicial de funcionários.
     */
    @PostConstruct
    public void init() {
        this.funcionario = new Funcionario();
        listar();
    }

    /**
     * Carrega todos os funcionários cadastrados.
     */
    public void listar() {
        this.funcionarios = funcionarioService.listarTodos();
    }

    /**
     * Salva ou atualiza o funcionário.
     * Exibe mensagens na interface utilizando FacesContext.
     */
    public void salvar() {
        try {
            funcionarioService.salvar(funcionario);
            FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Sucesso", "Funcionário salvo com sucesso"));

            // Atualiza a lista na tabela
            listar();

            // Limpa formulário
            funcionario = new Funcionario();

        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro", e.getMessage()));
        }
    }

    /**
     * Carrega o funcionário selecionado para edição.
     * Usado ao clicar no botão editar na tabela.
     */
    public void editar(Funcionario f) {
        this.funcionario = f;
    }

    /**
     * Remove o funcionário selecionado.
     */
//    public void remover(Funcionario f) {
//        try {
//            funcionarioService.remover(f);
//            listar();
//
//            FacesContext.getCurrentInstance()
//                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                "Removido", "Funcionário removido com sucesso"));
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance()
//                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                "Erro", e.getMessage()));
//        }
//    }

    // Getters e Setters

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

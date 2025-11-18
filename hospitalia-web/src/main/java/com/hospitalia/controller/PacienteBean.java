package com.hospitalia.controller;

import com.hospitalia.model.Paciente;
import com.hospitalia.service.PacienteService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PacienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PacienteService pacienteService;

    private Paciente paciente;
    private List<Paciente> pacientes;

    /**
     * Inicializa os dados da tela ao carregar a View.
     */
    @PostConstruct
    public void init() {
        paciente = new Paciente();
        carregarPacientes();
    }

    /**
     * Carrega todos os pacientes cadastrados.
     */
    public void carregarPacientes() {
        pacientes = pacienteService.listarTodos();
    }

    /**
     * Prepara um novo paciente para cadastro.
     */
    public void novo() {
        paciente = new Paciente();
    }

    /**
     * Salva ou atualiza um paciente no banco.
     */
    public void salvar() {
        try {
            pacienteService.salvar(paciente);
            carregarPacientes();
            adicionarMensagem("Paciente salvo com sucesso.");
            novo();
        } catch (Exception e) {
            adicionarMensagemErro("Erro ao salvar paciente: " + e.getMessage());
        }
    }

    /**
     * Remove um paciente pelo ID.
     */
    public void remover(Paciente pacienteSelecionado) {
        try {
            pacienteService.remover(pacienteSelecionado.getId());
            carregarPacientes();
            adicionarMensagem("Paciente removido com sucesso.");
        } catch (Exception e) {
            adicionarMensagemErro("Erro ao remover paciente: " + e.getMessage());
        }
    }

    /**
     * Adiciona mensagem de sucesso no JSF.
     */
    private void adicionarMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    /**
     * Adiciona mensagem de erro no JSF.
     */
    private void adicionarMensagemErro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    // Getters e Setters 

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}

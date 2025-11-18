package com.hospitalia.controller;

import com.hospitalia.model.Medico;
import com.hospitalia.service.MedicoService;

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
public class MedicoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private MedicoService medicoService;

    private Medico medico;
    private List<Medico> medicos;

    /**
     * Inicializa o estado da View ao carregá-la.
     */
    @PostConstruct
    public void init() {
        medico = new Medico();
        carregarMedicos();
    }

    /**
     * Carrega todos os médicos do banco.
     */
    public void carregarMedicos() {
        medicos = medicoService.listarTodos();
    }

    /**
     * Prepara a tela para um novo cadastro.
     */
    public void novo() {
        medico = new Medico();
    }

    /**
     * Salva ou atualiza um médico no banco.
     */
    public void salvar() {
        try {
            medicoService.salvar(medico);
            carregarMedicos();
            adicionarMensagem("Médico salvo com sucesso.");
            novo();
        } catch (Exception e) {
            adicionarMensagemErro("Erro ao salvar médico: " + e.getMessage());
        }
    }

    /**
     * Remove um médico utilizando seu ID.
     */
    public void remover(Medico medicoSelecionado) {
        try {
            medicoService.remover(medicoSelecionado.getId());
            carregarMedicos();
            adicionarMensagem("Médico removido com sucesso.");
        } catch (Exception e) {
            adicionarMensagemErro("Erro ao remover médico: " + e.getMessage());
        }
    }

    /**
     * Mensagem de sucesso no JSF.
     */
    private void adicionarMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    /**
     * Mensagem de erro no JSF.
     */
    private void adicionarMensagemErro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    // Getters e Setters -----------------------------------------------------

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }
}

package com.hospitalia.controller;

import com.hospitalia.model.Atendimento;
import com.hospitalia.model.Paciente;
import com.hospitalia.model.Medico;
import com.hospitalia.service.AtendimentoService;
import com.hospitalia.service.PacienteService;
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
public class AtendimentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoService atendimentoService;

    @Inject
    private PacienteService pacienteService;

    @Inject
    private MedicoService medicoService;

    private Atendimento atendimento;
    private List<Atendimento> atendimentos;

    private List<Paciente> pacientes;
    private List<Medico> medicos;

    @PostConstruct
    public void init() {
        atendimento = new Atendimento();
        carregarAtendimentos();
        carregarListasAuxiliares();
    }

    /**
     * Carrega todos os atendimentos existentes.
     */
    public void carregarAtendimentos() {
        atendimentos = atendimentoService.listarTodos();
    }

    /**
     * Carrega médicos e pacientes para selects.
     */
    public void carregarListasAuxiliares() {
        pacientes = pacienteService.listarTodos();
        medicos = medicoService.listarTodos();
    }

    /**
     * Prepara registro novo.
     */
    public void novo() {
        atendimento = new Atendimento();
    }

    /**
     * Salva ou atualiza.
     */
    public void salvar() {
        try {
            atendimentoService.salvar(atendimento);
            carregarAtendimentos();
            adicionarMensagem("Atendimento salvo com sucesso.");
            novo();
        } catch (Exception e) {
            adicionarMensagemErro("Erro ao salvar atendimento: " + e.getMessage());
        }
    }

    /**
     * Remove um atendimento pelo ID.
     */
    public void remover(Atendimento atendimentoSelecionado) {
        try {
            atendimentoService.remover(atendimentoSelecionado.getId());
            carregarAtendimentos();
            adicionarMensagem("Atendimento removido com sucesso.");
        } catch (Exception e) {
            adicionarMensagemErro("Erro ao remover atendimento: " + e.getMessage());
        }
    }

    private void adicionarMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    private void adicionarMensagemErro(String msg) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    // Getters e Setters -----------------------------------------------------

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }
}

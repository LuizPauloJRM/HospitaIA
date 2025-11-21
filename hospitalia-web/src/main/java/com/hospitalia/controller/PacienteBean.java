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
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class PacienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PacienteService pacienteService;

    private Paciente paciente;
    private List<Paciente> pacientes;
    
 // Lista fixa de sintomas predefinidos
    private List<String> sintomasPredefinido = Arrays.asList(
    "Dor de cabeça",
    "Febre",
    "Tosse",
    "Falta de ar",
    "Náusea",
    "Dor abdominal",
    "Tontura",
    "Cansaço",
    "Dor muscular"
    );

    /**
     * Inicializa os dados da tela ao carregar a View.
     */
    @PostConstruct
    public void init() {
        novo(); // garante sempre um objeto limpo
        carregarPacientes();
    }

    /**
     * Edita um paciente selecionado da tabela.
     */
    public void editar(Paciente pacienteSelecionado) {
        // Aqui evita erro de entidade detached
        this.paciente = pacienteService.buscarPorId(pacienteSelecionado.getId());
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

    /** Mensagens */
    private void adicionarMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    private void adicionarMensagemErro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    /** Getters e Setters */

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

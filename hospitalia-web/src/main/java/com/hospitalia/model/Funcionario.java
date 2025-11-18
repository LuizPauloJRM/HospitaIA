package com.hospitalia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Identificador único no banco de dados
     * Gerado automaticamente com auto incremento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Nome completo do funcionário
     */
    @Column(nullable = false, length = 150)
    private String nome;

    /*
     * Documento CPF do funcionário.
     * Opcionalmente você pode validar via regex ou anotação customizada.
     */
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    /*
     * Cargo ou função que o funcionário desempenha.
     */
    @Column(nullable = false, length = 100)
    private String cargo;

    /*
     * Data de admissão do funcionário no sistema.
     */
    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    /*
     * Salário do funcionário
     */
    @Column(nullable = false)
    private Double salario;

    public Funcionario() {
    }
    /*Getters e setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}

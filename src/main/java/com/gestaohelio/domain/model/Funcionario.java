package com.gestaohelio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do funcionário é obrigatório.")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String nome;

    @NotBlank(message = "O cargo é obrigatório.")
    @Size(max = 60, message = "O cargo deve ter no máximo 60 caracteres.")
    @Column(nullable = false, length = 60)
    private String cargo;

    public Funcionario (){
    }

    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome do funcionário é obrigatório.") @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome do funcionário é obrigatório.") @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O cargo é obrigatório.") @Size(max = 60, message = "O cargo deve ter no máximo 60 caracteres.") String getCargo() {
        return cargo;
    }

    public void setCargo(@NotBlank(message = "O cargo é obrigatório.") @Size(max = 60, message = "O cargo deve ter no máximo 60 caracteres.") String cargo) {
        this.cargo = cargo;
    }
}

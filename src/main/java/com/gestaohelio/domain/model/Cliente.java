package com.gestaohelio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "clientes",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_clientes_cpf_cnpj", columnNames = "cpf_cnpj")
        }
)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String nome;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
    @Column(nullable = false, length = 20)
    private String telefone;

    @Email(message = "E-mail inválido.")
    @Size(max = 120)
    @Column(length = 120)
    private String email;

    @Size(max = 18, message = "O CPF/CNPJ deve ter no máximo 18 caracteres.")
    @Column(name = "cpf_cnpj", nullable = true, length = 18)
    private String cpfCnpj;

    public Cliente() {
    }

    public Cliente(String nome, String telefone, String email, String cpfCnpj) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome é obrigatório.") @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O telefone é obrigatório.") @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank(message = "O telefone é obrigatório.") @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.") String telefone) {
        this.telefone = telefone;
    }

    public @Email(message = "E-mail inválido.") @Size(max = 120) String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "E-mail inválido.") @Size(max = 120) String email) {
        this.email = email;
    }

    public @Size(max = 18, message = "O CPF/CNPJ deve ter no máximo 18 caracteres.") String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(@Size(max = 18, message = "O CPF/CNPJ deve ter no máximo 18 caracteres.") String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}

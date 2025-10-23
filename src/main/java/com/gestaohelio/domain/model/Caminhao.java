package com.gestaohelio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "caminhoes")
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A placa é obrigatória.")
    @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres.")
    @Column(nullable = false, length = 10)
    private String placa;

    @NotBlank(message = "O modelo é obrigatório.")
    @Size(max = 60, message = "O modelo deve ter no máximo 60 caracteres.")
    @Column(nullable = false, length = 60)
    private String modelo;


    @ManyToOne(fetch = FetchType.LAZY) //muitos caminhões para um cliente
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_caminhao_cliente"))
    private Cliente cliente;

    public Caminhao(){
    }

    public Caminhao(String placa, String modelo, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public @NotBlank(message = "O modelo é obrigatório.") @Size(max = 60, message = "O modelo deve ter no máximo 60 caracteres.") String getModelo() {
        return modelo;
    }

    public void setModelo(@NotBlank(message = "O modelo é obrigatório.") @Size(max = 60, message = "O modelo deve ter no máximo 60 caracteres.") String modelo) {
        this.modelo = modelo;
    }

    public @NotBlank(message = "A placa é obrigatória.") @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres.") String getPlaca() {
        return placa;
    }

    public void setPlaca(@NotBlank(message = "A placa é obrigatória.") @Size(max = 10, message = "A placa deve ter no máximo 10 caracteres.") String placa) {
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

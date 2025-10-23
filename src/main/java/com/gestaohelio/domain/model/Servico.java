package com.gestaohelio.domain.model;

import com.gestaohelio.domain.enums.StatusServico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="A descrição do serviço é obrigatório.")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    @Column(nullable = false, length = 255 )
    private String descricao;

    @NotNull (message = "O valor do serviço é obrigatório")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusServico status;

    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caminhao_id", foreignKey = @ForeignKey(name = "fk_servico_caminhao"))
    private Caminhao caminhao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", foreignKey = @ForeignKey(name = "fk_servico_funcionario"))
    private Funcionario funcionario;

    public Servico() {
        this.status = StatusServico.ABERTO; // padrão ao criar
        this.dataEntrada = LocalDateTime.now(); // registra automaticamente a entrada
    }

    public @NotNull(message = "O valor do serviço é obrigatório") BigDecimal getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "O valor do serviço é obrigatório") BigDecimal valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "A descrição do serviço é obrigatório.") @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição do serviço é obrigatório.") @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres") String descricao) {
        this.descricao = descricao;
    }

    public StatusServico getStatus() {
        return status;
    }

    public void setStatus(StatusServico status) {
        this.status = status;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}

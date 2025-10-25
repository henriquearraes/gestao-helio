package com.gestaohelio.api.mapper;

import com.gestaohelio.api.dto.ServicoRequestDTO;
import com.gestaohelio.api.dto.ServicoResponseDTO;
import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.domain.model.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper (componentModel = "spring")
public interface ServicoMapper {

    // Do DTO de Entrada para Entidade
    @Mapping(source = "caminhaoId", target = "caminhao.id")
    @Mapping(source = "funcionarioId", target = "funcionario.id")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatus")
    Servico toEntity(ServicoRequestDTO dto);

    // Da Entidade para o DTO de Resposta
    @Mapping(source = "caminhao.placa", target = "caminhaoPlaca")
    @Mapping(source = "funcionario.nome", target = "funcionarioNome")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString") //qualifiedByName cria função especial
    ServicoResponseDTO toResponseDTO(Servico servico);


    // Transforma de String para ENUM e retorna um fallback padrão caso apresente falha
    @Named("mapStatus")
    default StatusServico mapStatus(String status) {
        if (status == null) return null;
        try {
            return StatusServico.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) { // Tratar isto depois
            return StatusServico.ABERTO; // fallback padrão
        }
    }

    // Transforma de ENUM para String
    @Named("statusToString")
    default String statusToString(StatusServico status) {
        return status != null ? status.name() : null;
    }

}

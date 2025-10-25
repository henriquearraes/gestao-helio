package com.gestaohelio.api.mapper;

import com.gestaohelio.api.dto.FuncionarioRequestDTO;
import com.gestaohelio.api.dto.FuncionarioResponseDTO;
import com.gestaohelio.domain.model.Funcionario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    // Converte DTO de ENTRADA para Entidade
    Funcionario toEntity (FuncionarioRequestDTO dto);

    // Converte Entidade para DTO de Resposta
    FuncionarioResponseDTO toResponseDTO (Funcionario entity);
}

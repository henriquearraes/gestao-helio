package com.gestaohelio.api.mapper;

import com.gestaohelio.api.dto.ClienteRequestDTO;
import com.gestaohelio.api.dto.ClienteResponseDTO;
import com.gestaohelio.domain.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    // Converte de DTO de ENTRADA para Entidade
    Cliente toEntity (ClienteRequestDTO dto);

    // Converte de Entidade para DTO de Resposta
    ClienteResponseDTO toResponseDto (Cliente entity);
}

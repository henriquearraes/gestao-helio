package com.gestaohelio.api.mapper;

import com.gestaohelio.api.dto.CaminhaoRequestDTO;
import com.gestaohelio.api.dto.CaminhaoResponseDTO;
import com.gestaohelio.domain.model.Caminhao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface CaminhaoMapper {

    //Converte DTO de Entrada para Entidade
    @Mapping(source = "clienteId", target = "cliente.id")
    Caminhao toEntity (CaminhaoRequestDTO dto);

    // Converte Entidade para DTO de Resposta
    @Mapping(source = "cliente.nome", target = "clienteNome")
    CaminhaoResponseDTO toResponseDTO (Caminhao entity);

}

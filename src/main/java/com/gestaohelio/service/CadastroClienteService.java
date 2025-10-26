package com.gestaohelio.service;

import com.gestaohelio.api.dto.ClienteRequestDTO;
import com.gestaohelio.api.dto.ClienteResponseDTO;
import com.gestaohelio.api.mapper.ClienteMapper;
import com.gestaohelio.domain.model.Cliente;
import com.gestaohelio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper mapper;

    @Autowired
    public CadastroClienteService(ClienteRepository clienteRepository, ClienteMapper mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public List<ClienteResponseDTO> listarTodos(){
        return clienteRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return mapper.toResponseDto(cliente);
    }

    public ClienteResponseDTO salvar (ClienteRequestDTO dto){
        Cliente cliente = mapper.toEntity(dto);
        clienteRepository.save(cliente);
        return mapper.toResponseDto(cliente);
    }

    public ClienteResponseDTO atualizar (Long id, ClienteRequestDTO dtoAtualizado){
        Cliente cliente = mapper.toEntity(dtoAtualizado);
        clienteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado! ID: "+id));
        clienteRepository.save(cliente);
        return mapper.toResponseDto(cliente);
    }

    public void excluir(Long id){
        if (!clienteRepository.existsById(id)){
            throw new RuntimeException("Cliente não encontrado! ID: "+id);
        }
        clienteRepository.deleteById(id);
    }
}

package com.gestaohelio.service;

import com.gestaohelio.api.dto.CaminhaoRequestDTO;
import com.gestaohelio.api.dto.CaminhaoResponseDTO;
import com.gestaohelio.api.mapper.CaminhaoMapper;
import com.gestaohelio.domain.model.Caminhao;
import com.gestaohelio.domain.model.Cliente;
import com.gestaohelio.repository.CaminhaoRepository;
import com.gestaohelio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroCaminhaoService {

    private final CaminhaoRepository caminhaoRepository;
    private final ClienteRepository clienteRepository;
    private final CaminhaoMapper mapper;

    @Autowired
    public CadastroCaminhaoService(CaminhaoRepository caminhaoRepository,
                                   ClienteRepository clienteRepository,
                                   CaminhaoMapper mapper) {
        this.caminhaoRepository = caminhaoRepository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public List<CaminhaoResponseDTO> listarTodos() {
        return caminhaoRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CaminhaoResponseDTO buscarPorId(Long id) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caminhão não encontrado com ID: "+id));
        return mapper.toResponseDTO(caminhao);
    }

    public CaminhaoResponseDTO salvar(CaminhaoRequestDTO dto,
                                      Long clienteId) {
        try {
            if (clienteId != null) {
                Optional<Cliente> cliente = clienteRepository.findById(clienteId);
                if (cliente.isEmpty()) {
                    throw new RuntimeException("Cliente não encontrado com ID: " + clienteId);
                }
                Caminhao caminhao = mapper.toEntity(dto);
                caminhao.setCliente(cliente.get());
                caminhaoRepository.save(caminhao);
                return mapper.toResponseDTO(caminhao);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Id do Cliente não pode ser nulo!");
        }
        Caminhao caminhao = mapper.toEntity(dto);
        caminhaoRepository.save(caminhao);
        return mapper.toResponseDTO(caminhao);

    }

    public CaminhaoResponseDTO atualizar(Long id, CaminhaoRequestDTO dtoAtualizado) {
        Caminhao caminhao = mapper.toEntity(dtoAtualizado);
        caminhaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caminhão não encontrado! ID: "+id));
        caminhaoRepository.save(caminhao);
        return mapper.toResponseDTO(caminhao);
    }

    public void excluir(Long id) {
        if (!caminhaoRepository.existsById(id)) {
            throw new RuntimeException("Caminhão não encontrado com ID: " + id);
        }
        caminhaoRepository.deleteById(id);
    }
}

package com.gestaohelio.service;

import com.gestaohelio.api.dto.FuncionarioRequestDTO;
import com.gestaohelio.api.dto.FuncionarioResponseDTO;
import com.gestaohelio.api.mapper.FuncionarioMapper;
import com.gestaohelio.domain.model.Funcionario;
import com.gestaohelio.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroFuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioMapper mapper;

    @Autowired
    public CadastroFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map((mapper::toResponseDTO))
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException ("Funcionário não encontrado! ID: "+id));
        return mapper.toResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO salvar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = mapper.toEntity(dto);
        funcionarioRepository.save(funcionario);
        return mapper.toResponseDTO(funcionario);
    }

    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = mapper.toEntity(dto);
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado! ID: "+id));
        funcionarioRepository.save(funcionario);
        return mapper.toResponseDTO(funcionario);
    }

    public void excluir(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado! ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}

package com.gestaohelio.service;

import com.gestaohelio.api.dto.FuncionarioRequestDTO;
import com.gestaohelio.api.dto.FuncionarioResponseDTO;
import com.gestaohelio.api.mapper.FuncionarioMapper;
import com.gestaohelio.common.exceptions.ElementoNaoEncontradoException;
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
    private final FuncionarioMapper mapper;

    @Autowired
    public CadastroFuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper mapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.mapper = mapper;
    }

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map((mapper::toResponseDTO))
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(ElementoNaoEncontradoException::new);
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
                .orElseThrow(ElementoNaoEncontradoException::new);
        funcionarioRepository.save(funcionario);
        return mapper.toResponseDTO(funcionario);
    }

    public void excluir(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ElementoNaoEncontradoException();
        }
        funcionarioRepository.deleteById(id);
    }
}

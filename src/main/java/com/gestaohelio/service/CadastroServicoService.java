package com.gestaohelio.service;

import com.gestaohelio.api.dto.ServicoRequestDTO;
import com.gestaohelio.api.dto.ServicoResponseDTO;
import com.gestaohelio.api.mapper.ServicoMapper;
import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.domain.model.Caminhao;
import com.gestaohelio.domain.model.Funcionario;
import com.gestaohelio.domain.model.Servico;
import com.gestaohelio.repository.CaminhaoRepository;
import com.gestaohelio.repository.FuncionarioRepository;
import com.gestaohelio.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CadastroServicoService {

    private final ServicoRepository servicoRepository;
    private final CaminhaoRepository caminhaoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ServicoMapper mapper;

    @Autowired
    public CadastroServicoService(ServicoRepository servicoRepository,
                                  CaminhaoRepository caminhaoRepository,
                                  FuncionarioRepository funcionarioRepository,
                                  ServicoMapper mapper) {
        this.servicoRepository = servicoRepository;
        this.caminhaoRepository = caminhaoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.mapper = mapper;
    }

    public List<ServicoResponseDTO> listarTodos() {
        return servicoRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ServicoResponseDTO buscarPorId(Long id) {
        Servico servico = servicoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado! ID: "+id));
        return mapper.toResponseDTO(servico);
    }


    public ServicoResponseDTO salvar(ServicoRequestDTO dto, Long caminhaoId, Long funcionarioId) {

            // vincula caminhao
            Optional<Caminhao> caminhao = caminhaoRepository.findById(caminhaoId);
            if (caminhao.isEmpty()) {
                throw new RuntimeException("Caminhão não encontrado com ID: " + caminhaoId);
            }

            // vincula funcionario
            Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioId);
            if (funcionario.isEmpty()) {
                throw new RuntimeException("Funcionário não encontrado com ID: " + funcionarioId);
            }

            Servico servico = mapper.toEntity(dto);
            servico.setCaminhao(caminhao.get());
            servico.setFuncionario(funcionario.get());
            servicoRepository.save(servico);
            return mapper.toResponseDTO(servico);

    }


    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto) {
        Servico servicoExistente = mapper.toEntity(dto);

        servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servico não encontrado! ID: "+id));

        // regra: se o status for CONCLUIDO, definir data de saida
        if (servicoExistente.getStatus() == StatusServico.CONCLUIDO) {
            if (servicoExistente.getDataEntrada() != null &&
                    LocalDateTime.now().isBefore(servicoExistente.getDataEntrada())) {
                throw new RuntimeException("A data de saída não pode ser anterior à data de entrada.");
            }
            servicoExistente.setDataSaida(LocalDateTime.now());
        }

        servicoRepository.save(servicoExistente);
        return mapper.toResponseDTO(servicoExistente);
    }


    public void excluir(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado com ID: " + id);
        }
        servicoRepository.deleteById(id);
    }


    public List<ServicoResponseDTO> listarPorStatus(StatusServico status) {
        return servicoRepository.findByStatus(status);
    }
}

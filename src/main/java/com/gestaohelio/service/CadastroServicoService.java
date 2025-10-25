package com.gestaohelio.service;

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

@Service
public class CadastroServicoService {

    private final ServicoRepository servicoRepository;
    private final CaminhaoRepository caminhaoRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public CadastroServicoService(ServicoRepository servicoRepository,
                                  CaminhaoRepository caminhaoRepository,
                                  FuncionarioRepository funcionarioRepository) {
        this.servicoRepository = servicoRepository;
        this.caminhaoRepository = caminhaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }


    public Servico salvar(Servico servico, Long caminhaoId, Long funcionarioId) {
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

        servico.setCaminhao(caminhao.get());
        servico.setFuncionario(funcionario.get());
        servico.setStatus(StatusServico.ABERTO);
        servico.setDataEntrada(LocalDateTime.now());
        servico.setDataSaida(null);

        return servicoRepository.save(servico);
    }


    public Servico atualizar(Long id, Servico servicoAtualizado) {
        Optional<Servico> servicoExistente = servicoRepository.findById(id);

        if (servicoExistente.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado com ID: " + id);
        }

        Servico servico = servicoExistente.get();

        servico.setDescricao(servicoAtualizado.getDescricao());
        servico.setValor(servicoAtualizado.getValor());
        servico.setStatus(servicoAtualizado.getStatus());

        // regra: se o status for CONCLUIDO, definir data de saida
        if (servicoAtualizado.getStatus() == StatusServico.CONCLUIDO) {
            if (servico.getDataEntrada() != null &&
                    LocalDateTime.now().isBefore(servico.getDataEntrada())) {
                throw new RuntimeException("A data de saída não pode ser anterior à data de entrada.");
            }
            servico.setDataSaida(LocalDateTime.now());
        }

        return servicoRepository.save(servico);
    }


    public void excluir(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado com ID: " + id);
        }
        servicoRepository.deleteById(id);
    }


    public List<Servico> listarPorStatus(StatusServico status) {
        return servicoRepository.findByStatus(status);
    }
}

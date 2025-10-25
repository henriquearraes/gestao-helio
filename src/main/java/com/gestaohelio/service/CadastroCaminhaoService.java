package com.gestaohelio.service;

import com.gestaohelio.domain.model.Caminhao;
import com.gestaohelio.domain.model.Cliente;
import com.gestaohelio.repository.CaminhaoRepository;
import com.gestaohelio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroCaminhaoService {

    private final CaminhaoRepository caminhaoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CadastroCaminhaoService(CaminhaoRepository caminhaoRepository, ClienteRepository clienteRepository) {
        this.caminhaoRepository = caminhaoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Caminhao> listarTodos() {
        return caminhaoRepository.findAll();
    }

    public Optional<Caminhao> buscarPorId(Long id) {
        return caminhaoRepository.findById(id);
    }

    public Caminhao salvar(Caminhao caminhao, Long clienteId) {
        if (clienteId != null) {
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            if (cliente.isEmpty()) {
                throw new RuntimeException("Cliente não encontrado com ID: " + clienteId);
            }
            caminhao.setCliente(cliente.get());
        }
        return caminhaoRepository.save(caminhao);
    }

    public Caminhao atualizar(Long id, Caminhao caminhaoAtualizado) {
        Optional<Caminhao> caminhaoExistente = caminhaoRepository.findById(id);

        if (caminhaoExistente.isEmpty()) {
            throw new RuntimeException("Caminhão não encontrado com ID: " + id);
        }

        Caminhao caminhao = caminhaoExistente.get();
        caminhao.setPlaca(caminhaoAtualizado.getPlaca());
        caminhao.setModelo(caminhaoAtualizado.getModelo());
        caminhao.setCliente(caminhaoAtualizado.getCliente());

        return caminhaoRepository.save(caminhao);
    }

    public void excluir(Long id) {
        if (!caminhaoRepository.existsById(id)) {
            throw new RuntimeException("Caminhão não encontrado com ID: " + id);
        }
        caminhaoRepository.deleteById(id);
    }
}

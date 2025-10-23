package com.gestaohelio.controller;

import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.domain.model.Servico;
import com.gestaohelio.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> servicos = servicoService.listarTodos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarPorId(id);
        return servico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@RequestParam Long caminhaoId, @RequestParam Long funcionarioId, @RequestBody Servico servico) {
        try {
            Servico novoServico = servicoService.salvar(servico, caminhaoId, funcionarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servicoAtualizado) {
        try {
            Servico atualizado = servicoService.atualizar(id, servicoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            servicoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<Servico>> listarPorStatus(@RequestParam StatusServico status) {
        List<Servico> servicos = servicoService.listarPorStatus(status);
        return ResponseEntity.ok(servicos);
    }
}

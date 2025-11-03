package com.gestaohelio.controller;

import com.gestaohelio.api.dto.ServicoRequestDTO;
import com.gestaohelio.api.dto.ServicoResponseDTO;
import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.service.CadastroServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final CadastroServicoService cadastroServicoService;


    @Autowired
    public ServicoController(CadastroServicoService cadastroServicoService) {
        this.cadastroServicoService = cadastroServicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarTodos() {
        List<ServicoResponseDTO> servicos = cadastroServicoService.listarTodos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> buscarPorId(@PathVariable Long id) {
            ServicoResponseDTO servico = cadastroServicoService.buscarPorId(id);
            return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criar(@RequestParam Long caminhaoId,
                                                    @RequestParam Long funcionarioId,
                                                    @RequestBody @Valid ServicoRequestDTO dto) {
            ServicoResponseDTO servico = cadastroServicoService.salvar(dto, caminhaoId, funcionarioId);
            return ResponseEntity.ok(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid ServicoRequestDTO dto) {
            ServicoResponseDTO atualizado = cadastroServicoService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
            cadastroServicoService.excluir(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public ResponseEntity<List<ServicoResponseDTO>> listarPorStatus(@RequestParam StatusServico status) {
        List<ServicoResponseDTO> servicos = cadastroServicoService.listarPorStatus(status);
        return ResponseEntity.ok(servicos);
    }
}

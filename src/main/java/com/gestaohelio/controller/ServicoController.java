package com.gestaohelio.controller;

import com.gestaohelio.api.dto.ServicoRequestDTO;
import com.gestaohelio.api.dto.ServicoResponseDTO;
import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.service.CadastroServicoService;
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
        try {
            ServicoResponseDTO servico = cadastroServicoService.buscarPorId(id);
            return ResponseEntity.ok(servico);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criar(@RequestParam Long caminhaoId,
                                                    @RequestParam Long funcionarioId,
                                                    @RequestBody ServicoRequestDTO dto) {
        try {
            ServicoResponseDTO servico = cadastroServicoService.salvar(dto, caminhaoId, funcionarioId);
            return ResponseEntity.ok(servico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody ServicoRequestDTO dto) {
        try {
            ServicoResponseDTO atualizado = cadastroServicoService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            cadastroServicoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<ServicoResponseDTO>> listarPorStatus(@RequestParam StatusServico status) {
        List<ServicoResponseDTO> servicos = cadastroServicoService.listarPorStatus(status);
        return ResponseEntity.ok(servicos);
    }
}

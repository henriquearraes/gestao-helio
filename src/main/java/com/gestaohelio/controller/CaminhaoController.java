package com.gestaohelio.controller;

import com.gestaohelio.api.dto.CaminhaoRequestDTO;
import com.gestaohelio.api.dto.CaminhaoResponseDTO;
import com.gestaohelio.domain.model.Caminhao;
import com.gestaohelio.service.CadastroCaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    private final CadastroCaminhaoService cadastroCaminhaoService;

    @Autowired
    public CaminhaoController(CadastroCaminhaoService cadastroCaminhaoService) {
        this.cadastroCaminhaoService = cadastroCaminhaoService;
    }


    @GetMapping
    public ResponseEntity<List<CaminhaoResponseDTO>> listarTodos() {
        List<CaminhaoResponseDTO> caminhoes = cadastroCaminhaoService.listarTodos();
        return ResponseEntity.ok(caminhoes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cadastroCaminhaoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CaminhaoResponseDTO> criar(@RequestParam(required = false) Long clienteId,
                                                     @RequestBody CaminhaoRequestDTO dto) {
        try {
            return ResponseEntity.ok(cadastroCaminhaoService.salvar(dto, clienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody CaminhaoRequestDTO dto) {
        try {
            CaminhaoResponseDTO caminhao = cadastroCaminhaoService.atualizar(id, dto);
            return ResponseEntity.ok(caminhao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            cadastroCaminhaoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.gestaohelio.controller;

import com.gestaohelio.api.dto.CaminhaoRequestDTO;
import com.gestaohelio.api.dto.CaminhaoResponseDTO;
import com.gestaohelio.domain.model.Caminhao;
import com.gestaohelio.service.CadastroCaminhaoService;
import jakarta.validation.Valid;
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
            return ResponseEntity.ok(cadastroCaminhaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CaminhaoResponseDTO> criar(@RequestParam(required = false) Long clienteId,
                                                     @RequestBody @Valid CaminhaoRequestDTO dto) {
            return ResponseEntity.ok(cadastroCaminhaoService.salvar(dto, clienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid CaminhaoRequestDTO dto) {
            CaminhaoResponseDTO caminhao = cadastroCaminhaoService.atualizar(id, dto);
            return ResponseEntity.ok(caminhao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
            cadastroCaminhaoService.excluir(id);
            return ResponseEntity.noContent().build();
    }
}

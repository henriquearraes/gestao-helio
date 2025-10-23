package com.gestaohelio.controller;

import com.gestaohelio.domain.model.Caminhao;
import com.gestaohelio.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    private final CaminhaoService caminhaoService;

    @Autowired
    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }


    @GetMapping
    public ResponseEntity<List<Caminhao>> listarTodos() {
        List<Caminhao> caminhoes = caminhaoService.listarTodos();
        return ResponseEntity.ok(caminhoes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> buscarPorId(@PathVariable Long id) {
        Optional<Caminhao> caminhao = caminhaoService.buscarPorId(id);
        return caminhao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Caminhao> criar(@RequestParam(required = false) Long clienteId, @RequestBody Caminhao caminhao) {
        try {
            Caminhao novoCaminhao = caminhaoService.salvar(caminhao, clienteId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCaminhao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> atualizar(@PathVariable Long id, @RequestBody Caminhao caminhao) {
        try {
            Caminhao atualizado = caminhaoService.atualizar(id, caminhao);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            caminhaoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

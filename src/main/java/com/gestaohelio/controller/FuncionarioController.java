package com.gestaohelio.controller;

import com.gestaohelio.domain.model.Funcionario;
import com.gestaohelio.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        List<Funcionario> funcionarios = funcionarioService.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
        return funcionario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> criar(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.salvar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            Funcionario atualizado = funcionarioService.atualizar(id, funcionario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            funcionarioService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

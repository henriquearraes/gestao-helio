package com.gestaohelio.controller;

import com.gestaohelio.api.dto.ClienteResponseDTO;
import com.gestaohelio.api.dto.FuncionarioRequestDTO;
import com.gestaohelio.api.dto.FuncionarioResponseDTO;
import com.gestaohelio.domain.model.Cliente;
import com.gestaohelio.domain.model.Funcionario;
import com.gestaohelio.service.CadastroFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final CadastroFuncionarioService cadastroFuncionarioService;

    @Autowired
    public FuncionarioController(CadastroFuncionarioService cadastroFuncionarioService) {
        this.cadastroFuncionarioService = cadastroFuncionarioService;
    }


    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        List<FuncionarioResponseDTO> funcionarios = cadastroFuncionarioService.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(cadastroFuncionarioService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody FuncionarioRequestDTO dto) {

        FuncionarioResponseDTO funcionario = cadastroFuncionarioService.salvar(dto);
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Long id,
                                                            @RequestBody FuncionarioRequestDTO dto) {
        try {
            FuncionarioResponseDTO funcionario = cadastroFuncionarioService.atualizar(id, dto);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            cadastroFuncionarioService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

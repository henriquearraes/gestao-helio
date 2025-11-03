package com.gestaohelio.controller;

import com.gestaohelio.api.dto.ClienteRequestDTO;
import com.gestaohelio.api.dto.ClienteResponseDTO;
import com.gestaohelio.common.exceptions.ElementoNaoEncontradoException;
import com.gestaohelio.domain.model.Cliente;
import com.gestaohelio.service.CadastroClienteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CadastroClienteService cadastroClienteService;

    @Autowired
    public ClienteController(CadastroClienteService cadastroClienteService) {
        this.cadastroClienteService = cadastroClienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteResponseDTO> clientes = cadastroClienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {

            return ResponseEntity.ok(cadastroClienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody @Valid ClienteRequestDTO dto) {
        ClienteResponseDTO novoCliente = cadastroClienteService.salvar(dto);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid ClienteRequestDTO dto) {
            ClienteResponseDTO atualizado = cadastroClienteService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
            cadastroClienteService.excluir(id);
            return ResponseEntity.noContent().build();
    }
}

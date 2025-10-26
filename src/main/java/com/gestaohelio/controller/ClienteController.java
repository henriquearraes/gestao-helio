package com.gestaohelio.controller;

import com.gestaohelio.api.dto.ClienteRequestDTO;
import com.gestaohelio.api.dto.ClienteResponseDTO;
import com.gestaohelio.domain.model.Cliente;
import com.gestaohelio.service.CadastroClienteService;
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
        try {
            return ResponseEntity.ok(cadastroClienteService.buscarPorId(id));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO novoCliente = cadastroClienteService.salvar(dto);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody ClienteRequestDTO dto) {
        try {
            ClienteResponseDTO atualizado = cadastroClienteService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            cadastroClienteService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

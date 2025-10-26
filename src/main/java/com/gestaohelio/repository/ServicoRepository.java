package com.gestaohelio.repository;

import com.gestaohelio.api.dto.ServicoResponseDTO;
import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<ServicoResponseDTO> findByStatus(StatusServico status);
}

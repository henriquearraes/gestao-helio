package com.gestaohelio.repository;

import com.gestaohelio.domain.enums.StatusServico;
import com.gestaohelio.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByStatus(StatusServico status);
}

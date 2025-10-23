package com.gestaohelio.repository;

import com.gestaohelio.domain.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
}

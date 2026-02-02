package br.com.desafiovotacao.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiovotacao.entity.ResultadoVotacaoPautaEntity;

public interface ResultadoVotacaoPautaRepository extends JpaRepository<ResultadoVotacaoPautaEntity, UUID>{

	Optional<ResultadoVotacaoPautaEntity> findByPautaId(UUID pautaId);

}

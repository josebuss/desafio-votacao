package br.com.desafiovotacao.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.desafiovotacao.entity.SessaoVotacaoEntity;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacaoEntity, UUID> {

	@Query("select s from SessaoVotacaoEntity s" + //
			" where (:pautaId is null or s.pauta.id = :pautaId) " + //
			" and (CAST(:ativa AS boolean) is null " + //
			" or (:ativa = true and s.fim >= :now) " + //
			" or (:ativa = false and s.fim < :now)) " + //
			" order by s.inicio ")
	Page<SessaoVotacaoEntity> findByPautaIdAndAtiva(@Param("pautaId") UUID pautaId, @Param("ativa") Boolean ativa,
			@Param("now") LocalDateTime now, Pageable pageable);
	
	@Query("select count(1) > 0 from SessaoVotacaoEntity s" + //
			" where s.pauta.id = :pautaId" + //
			" and s.fim >= :now")
	boolean existsByPautaIdAndAtiva(@Param("pautaId") UUID pautaId, @Param("now") LocalDateTime now);

	Optional<SessaoVotacaoEntity> findTopByPautaIdOrderByInicioDesc(UUID pautaId);

}

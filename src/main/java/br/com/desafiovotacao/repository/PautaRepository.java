package br.com.desafiovotacao.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.desafiovotacao.entity.PautaEntity;


public interface PautaRepository extends JpaRepository<PautaEntity, UUID>{
	
	Optional<PautaEntity> findByCodigo(String codigo);
	
	@Query("select a from PautaEntity a" +
			" join SessaoVotacaoEntity b on b.pauta = a where b.fim < :now" +
			" order by a.codigo")
	List<PautaEntity> findAllVotacaoEncerrada(@Param("now") LocalDateTime now);

}

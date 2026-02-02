package br.com.desafiovotacao.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiovotacao.entity.VotacaoEntity;
import jakarta.validation.constraints.NotNull;

public interface VotacaoRepository extends JpaRepository<VotacaoEntity, UUID> {

	boolean existsBySessaoVotacaoIdAndCpf(UUID id, @NotNull String cpf);

	Page<VotacaoEntity> findAllBySessaoVotacaoId(UUID sessaoVotacaoId, PageRequest pageRequest);

	Integer countBySessaoVotacaoIdAndAprova(UUID id, boolean b);


}

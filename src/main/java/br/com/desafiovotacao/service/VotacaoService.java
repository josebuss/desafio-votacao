package br.com.desafiovotacao.service;

import java.util.List;
import java.util.UUID;

import br.com.desafiovotacao.dto.VotoRequest;
import br.com.desafiovotacao.entity.dto.VotacaoDTO;

public interface VotacaoService {

	VotacaoDTO criar(VotoRequest payload);

	List<VotacaoDTO> listar(int offset, int size, UUID sessaoVotacaoId);

}

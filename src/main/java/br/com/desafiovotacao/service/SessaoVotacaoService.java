package br.com.desafiovotacao.service;

import java.util.List;
import java.util.UUID;

import br.com.desafiovotacao.dto.AbrirSessaoRequest;
import br.com.desafiovotacao.entity.dto.SessaoVotacaoDTO;

public interface SessaoVotacaoService {

	SessaoVotacaoDTO criar(AbrirSessaoRequest payload);

	List<SessaoVotacaoDTO> listar(int offset, int size, UUID pautaId, Boolean ativa);

}

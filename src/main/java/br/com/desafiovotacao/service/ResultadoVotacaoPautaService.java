package br.com.desafiovotacao.service;

import java.util.List;
import java.util.UUID;

import br.com.desafiovotacao.entity.dto.ResultadoVotacaoPautaDTO;

public interface ResultadoVotacaoPautaService {

	List<ResultadoVotacaoPautaDTO> listar(int offset, int size, UUID pautaId);

}

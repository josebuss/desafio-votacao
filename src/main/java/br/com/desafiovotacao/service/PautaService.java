package br.com.desafiovotacao.service;

import java.util.List;

import br.com.desafiovotacao.entity.dto.PautaDTO;

public interface PautaService {

	PautaDTO criar(PautaDTO payload);

	List<PautaDTO> listar(int offset, int size, String codigo, String descricao);

}

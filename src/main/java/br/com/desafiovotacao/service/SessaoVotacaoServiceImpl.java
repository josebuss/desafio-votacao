package br.com.desafiovotacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.desafiovotacao.dto.AbrirSessaoRequest;
import br.com.desafiovotacao.entity.SessaoVotacaoEntity;
import br.com.desafiovotacao.entity.dto.SessaoVotacaoDTO;
import br.com.desafiovotacao.entity.dto.SessaoVotacaoMapper;
import br.com.desafiovotacao.repository.SessaoVotacaoRepository;
import br.com.desafiovotacao.validator.SessaoVotacaoValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

	private final SessaoVotacaoMapper sessaoVotacaoMapper;
	private final SessaoVotacaoRepository sessaoVotacaoRepository;
	private final SessaoVotacaoValidator sessaoVotacaoValidator;

	@Override
	public SessaoVotacaoDTO criar(AbrirSessaoRequest payload) {
		var pautaEntity = sessaoVotacaoValidator.validar(payload);

		long duracao = (payload.duracao() == null || payload.duracao() <= 0) ? 1 : payload.duracao();

		SessaoVotacaoEntity entity = new SessaoVotacaoEntity();
		entity.setPauta(pautaEntity);
		entity.setInicio(LocalDateTime.now());
		entity.setFim(entity.getInicio().plusMinutes(duracao));
		entity = sessaoVotacaoRepository.save(entity);
		return sessaoVotacaoMapper.toResponse(entity);
	}

	@Override
	public List<SessaoVotacaoDTO> listar(int offset, int size, UUID pautaId, Boolean ativa) {
		return sessaoVotacaoRepository
				.findByPautaIdAndAtiva(pautaId, ativa, LocalDateTime.now(), PageRequest.of(offset / size, size)) //
				.map(sessaoVotacaoMapper::toResponse) //
				.toList();
	}

}

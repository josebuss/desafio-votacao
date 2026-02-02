package br.com.desafiovotacao.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.desafiovotacao.dto.VotoRequest;
import br.com.desafiovotacao.entity.VotacaoEntity;
import br.com.desafiovotacao.entity.dto.VotacaoDTO;
import br.com.desafiovotacao.entity.dto.VotacaoMapper;
import br.com.desafiovotacao.repository.VotacaoRepository;
import br.com.desafiovotacao.validator.VotacaoValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

	private final VotacaoRepository votacaoRepository;
	private final VotacaoMapper votacaoMapper;
	private final VotacaoValidator votacaoValidator;

	@Override
	public VotacaoDTO criar(VotoRequest payload) {
		var sessaoEntity = votacaoValidator.validar(payload);

		var entity = new VotacaoEntity();
		entity.setSessaoVotacao(sessaoEntity);
		entity.setCpf(payload.cpf());
		entity.setAprova(payload.aprova());
		entity = votacaoRepository.save(entity);
		return votacaoMapper.toResponse(entity);
	}

	@Override
	public List<VotacaoDTO> listar(int offset, int size, UUID sessaoVotacaoId) {
		return votacaoRepository.findAllBySessaoVotacaoId(sessaoVotacaoId, PageRequest.of(offset / size, size)) //
				.map(votacaoMapper::toResponse) //
				.toList();
	}

}

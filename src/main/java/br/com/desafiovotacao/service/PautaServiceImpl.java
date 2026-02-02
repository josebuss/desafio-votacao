package br.com.desafiovotacao.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.desafiovotacao.entity.PautaEntity;
import br.com.desafiovotacao.entity.dto.PautaDTO;
import br.com.desafiovotacao.entity.dto.PautaMapper;
import br.com.desafiovotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

	private final PautaRepository pautaRepository;
	private final PautaMapper pautaMapper;

	@Override
	public PautaDTO criar(PautaDTO payload) {
		var entity = pautaMapper.toEntity(payload);
		entity = pautaRepository.save(entity);
		return pautaMapper.toResponse(entity);
	}

	@Override
	public List<PautaDTO> listar(int offset, int size, String codigo, String descricao) {
		var where = ExampleMatcher.matching()
	            .withIgnoreNullValues()
	            .withIgnoreCase()
	            .withMatcher("codigo", ExampleMatcher.GenericPropertyMatchers.exact())
	            .withMatcher("descricao", ExampleMatcher.GenericPropertyMatchers.contains());
		
		PautaEntity filtro = new PautaEntity();
		filtro.setCodigo(codigo);
		filtro.setDescricao(descricao);

		return pautaRepository.findAll(Example.of(filtro, where), PageRequest.of(offset / size, size, Sort.by(Direction.ASC, "codigo"))) //
				.map(pautaMapper::toResponse) //
				.toList();
	}
}

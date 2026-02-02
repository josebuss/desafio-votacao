package br.com.desafiovotacao.service;

import static br.com.desafiovotacao.entity.ResultadoVotacaoPautaEnum.APROVADO;
import static br.com.desafiovotacao.entity.ResultadoVotacaoPautaEnum.INCONCLUSIVO;
import static br.com.desafiovotacao.entity.ResultadoVotacaoPautaEnum.REPROVADO;
import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafiovotacao.entity.ResultadoVotacaoPautaEntity;
import br.com.desafiovotacao.entity.SessaoVotacaoEntity;
import br.com.desafiovotacao.entity.dto.ResultadoVotacaoPautaDTO;
import br.com.desafiovotacao.entity.dto.ResultadoVotacaoPautaMapper;
import br.com.desafiovotacao.repository.PautaRepository;
import br.com.desafiovotacao.repository.ResultadoVotacaoPautaRepository;
import br.com.desafiovotacao.repository.VotacaoRepository;
import br.com.desafiovotacao.validator.ResultadoVotacaoPautaValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultadoVotacaoPautaServiceImpl implements ResultadoVotacaoPautaService {

	private final PautaRepository pautaRepository;
	private final VotacaoRepository votacaoRepository;
	private final ResultadoVotacaoPautaRepository resultadoVotacaoPautaRepository;
	private final ResultadoVotacaoPautaMapper resultadoVotacaoPautaMapper;
	private final ResultadoVotacaoPautaValidator resultadoVotacaoPautaValidator;

	@Override
	public List<ResultadoVotacaoPautaDTO> listar(int offset, int size, UUID pautaId) {
		if (nonNull(pautaId)) {
			return List.of(buscarResultado(pautaId));
		}
		return pautaRepository.findAllVotacaoEncerrada(LocalDateTime.now()).stream() //
				.map(entity -> buscarResultado(entity.getId())) //
				.toList();
	}

	private ResultadoVotacaoPautaDTO buscarResultado(UUID pautaId) {
		var ultimaSessaoEntity = resultadoVotacaoPautaValidator.validar(pautaId);

		var resultado = resultadoVotacaoPautaRepository.findByPautaId(pautaId);
		if (resultado.isPresent() && resultado.get().getSessaoVotacao().getId().equals(ultimaSessaoEntity.getId())) {
			log.debug("Calculo para a pauta {} já realizado", pautaId);
			return resultadoVotacaoPautaMapper.toResponse(resultado.get());
		}
		return totalizarVotacaoPauta(ultimaSessaoEntity, resultado);
	}

	private ResultadoVotacaoPautaDTO totalizarVotacaoPauta(SessaoVotacaoEntity ultimaSessaoEntity,
			Optional<ResultadoVotacaoPautaEntity> resultado) {

		log.info("Realizando apuração da pauta {}", ultimaSessaoEntity.getPauta().getId());

		var entity = resultado.orElse(new ResultadoVotacaoPautaEntity());
		entity.setPauta(ultimaSessaoEntity.getPauta());
		entity.setSessaoVotacao(ultimaSessaoEntity);
		entity.setTotalSim(votacaoRepository.countBySessaoVotacaoIdAndAprova(ultimaSessaoEntity.getId(), true));
		entity.setTotalNao(votacaoRepository.countBySessaoVotacaoIdAndAprova(ultimaSessaoEntity.getId(), false));
		entity.setTotalVotos(entity.getTotalSim() + entity.getTotalNao());

		if (entity.getTotalSim().equals(entity.getTotalNao())) {
			entity.setResultado(INCONCLUSIVO);
		} else {
			entity.setResultado(entity.getTotalSim() > entity.getTotalNao() ? APROVADO : REPROVADO);
		}

		entity = resultadoVotacaoPautaRepository.save(entity);
		return resultadoVotacaoPautaMapper.toResponse(entity);
	}

}

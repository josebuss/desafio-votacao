package br.com.desafiovotacao.validator;

import static br.com.desafiovotacao.utils.MensagensConstants.NAO_E_POSSIVEL_APRESENTAR_O_RESULTADO_POIS_A_PAUTA_AINDA_ESTA_EM_ANDAMENTO;
import static br.com.desafiovotacao.utils.MensagensConstants.NAO_FOI_ENCONTRADO_NENHUMA_SESSAO_DE_VOTACAO_PARA_A_PAUTA_INFORMADA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.desafiovotacao.entity.SessaoVotacaoEntity;
import br.com.desafiovotacao.exception.ServiceException;
import br.com.desafiovotacao.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResultadoVotacaoPautaValidator {

	private final SessaoVotacaoRepository sessaoVotacaoRepository;

	public SessaoVotacaoEntity validar(UUID pautaId) {
		var entity = sessaoVotacaoRepository.findTopByPautaIdOrderByInicioDesc(pautaId) //
				.orElseThrow(() -> new ServiceException(NOT_FOUND,
						NAO_FOI_ENCONTRADO_NENHUMA_SESSAO_DE_VOTACAO_PARA_A_PAUTA_INFORMADA));

		if (entity.getFim().isAfter(LocalDateTime.now())) {
			throw new ServiceException(BAD_REQUEST,
					NAO_E_POSSIVEL_APRESENTAR_O_RESULTADO_POIS_A_PAUTA_AINDA_ESTA_EM_ANDAMENTO);
		}
		return entity;
	}

}

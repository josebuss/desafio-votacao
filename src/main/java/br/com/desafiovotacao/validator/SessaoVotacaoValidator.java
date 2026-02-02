package br.com.desafiovotacao.validator;

import static br.com.desafiovotacao.utils.MensagensConstants.JA_EXISTE_UMA_SESSAO_DE_VOTACAO_EM_ANDAMENTO;
import static br.com.desafiovotacao.utils.MensagensConstants.NAO_FOI_POSSIVEL_ENCONTRAR_A_PAUTA;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.desafiovotacao.dto.AbrirSessaoRequest;
import br.com.desafiovotacao.entity.PautaEntity;
import br.com.desafiovotacao.exception.ServiceException;
import br.com.desafiovotacao.repository.PautaRepository;
import br.com.desafiovotacao.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessaoVotacaoValidator {
	
	private final SessaoVotacaoRepository sessaoVotacaoRepository;
	private final PautaRepository pautaRepository;

	public PautaEntity validar(AbrirSessaoRequest payload) {
		var pautaEntity = pautaRepository.findById(payload.pautaId())
				.orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, NAO_FOI_POSSIVEL_ENCONTRAR_A_PAUTA));
		
		if (sessaoVotacaoRepository.existsByPautaIdAndAtiva(payload.pautaId(), LocalDateTime.now())) {
				throw new ServiceException(HttpStatus.BAD_REQUEST, JA_EXISTE_UMA_SESSAO_DE_VOTACAO_EM_ANDAMENTO);
		}
		return pautaEntity;
	}

}

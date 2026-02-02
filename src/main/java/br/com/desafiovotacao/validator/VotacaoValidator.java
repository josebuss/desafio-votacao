package br.com.desafiovotacao.validator;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.desafiovotacao.dto.VotoRequest;
import br.com.desafiovotacao.entity.SessaoVotacaoEntity;
import br.com.desafiovotacao.exception.ServiceException;
import br.com.desafiovotacao.repository.SessaoVotacaoRepository;
import br.com.desafiovotacao.repository.VotacaoRepository;
import br.com.desafiovotacao.utils.MensagensConstants;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VotacaoValidator {
	
	private final SessaoVotacaoRepository sessaoVotacaoRepository;
	private final VotacaoRepository votacaoRepository;

	public SessaoVotacaoEntity validar(VotoRequest payload) {
		var sessaoEntity = sessaoVotacaoRepository.findById(payload.sessaoId()) //
				.orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, MensagensConstants.SESSAO_DE_VOTACAO_NAO_ENCONTRADA));

		if (sessaoEntity.getFim().isBefore(LocalDateTime.now())) {
			throw new ServiceException(BAD_REQUEST, MensagensConstants.TEMPO_DE_VOTACAO_JA_ENCERRADO);
		}
		if (votacaoRepository.existsBySessaoVotacaoIdAndCpf(sessaoEntity.getId(), payload.cpf())) {
			throw new ServiceException(BAD_REQUEST, MensagensConstants.O_USUARIO_JA_VOTOU_NESTA_SESSAO);
		}
		
		return sessaoEntity;
	}

}

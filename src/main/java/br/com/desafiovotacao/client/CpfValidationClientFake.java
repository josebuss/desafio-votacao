package br.com.desafiovotacao.client;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.desafiovotacao.client.dto.CpfValidationResponse;
import br.com.desafiovotacao.client.dto.CpfVoteStatus;
import br.com.desafiovotacao.exception.ServiceException;

@Component
public class CpfValidationClientFake implements CpfValidationClient {

	private final Random random = new Random();

	@Override
	public CpfValidationResponse validarCpf(String cpf) {
		if (random.nextBoolean()) {
			throw new ServiceException(HttpStatus.NOT_FOUND, "CPF inválido");
		}
		return new CpfValidationResponse(random.nextBoolean() ? CpfVoteStatus.ABLE_TO_VOTE : CpfVoteStatus.UNABLE_TO_VOTE);
	}
}

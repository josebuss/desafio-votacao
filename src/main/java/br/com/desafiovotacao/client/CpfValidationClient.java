package br.com.desafiovotacao.client;

import br.com.desafiovotacao.client.dto.CpfValidationResponse;

public interface CpfValidationClient {

	CpfValidationResponse validarCpf(String cpf);

}

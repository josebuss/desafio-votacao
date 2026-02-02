package br.com.desafiovotacao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiovotacao.client.CpfValidationClient;
import br.com.desafiovotacao.client.dto.CpfValidationResponse;
import br.com.desafiovotacao.client.dto.CpfVoteStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/cpf")
@RequiredArgsConstructor
public class CpfValidationController {

	private final CpfValidationClient cpfValidationClient;

	@GetMapping("/{cpf}")
	public ResponseEntity<CpfValidationResponse> validarCpf(@PathVariable String cpf) {
		var response = cpfValidationClient.validarCpf(cpf);

		if (response.status() == CpfVoteStatus.UNABLE_TO_VOTE) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.ok(response);
	}

}

package br.com.desafiovotacao.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiovotacao.entity.dto.ResultadoVotacaoPautaDTO;
import br.com.desafiovotacao.service.ResultadoVotacaoPautaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/resultados")
@RequiredArgsConstructor
public class ResultadoVotacaoPautaController {

	private final ResultadoVotacaoPautaService service;

	@Operation(summary = "Listar resultado votação", description = "Lista o resultado da sessão de votação de uma ou mais pautas")
	@GetMapping
	public ResponseEntity<List<ResultadoVotacaoPautaDTO>> listar(@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) UUID pautaId) {
		return ResponseEntity.ok(service.listar(offset, size, pautaId));
	}
	
}

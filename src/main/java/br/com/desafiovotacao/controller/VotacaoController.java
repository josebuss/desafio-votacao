package br.com.desafiovotacao.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiovotacao.dto.VotoRequest;
import br.com.desafiovotacao.entity.dto.VotacaoDTO;
import br.com.desafiovotacao.service.VotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/votacoes")
@RequiredArgsConstructor
public class VotacaoController {
	
	private final VotacaoService service;
	
	@Operation(summary = "Criar voto", description = "Registra uma novo voto para a sessão")
	@PostMapping
	public ResponseEntity<VotacaoDTO> criar(@RequestBody @Valid VotoRequest payload) {
		VotacaoDTO criada = service.criar(payload);
		return ResponseEntity.status(HttpStatus.CREATED).body(criada);
	}
	
	@Operation(summary = "Listar votos", description = "Lista os votos para uma ou mais sessões")
	@GetMapping
	public ResponseEntity<List<VotacaoDTO>> listar(@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "10") int size, @RequestParam UUID sessaoId) {
		return ResponseEntity.ok(service.listar(offset, size, sessaoId));
	}

}

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

import br.com.desafiovotacao.dto.AbrirSessaoRequest;
import br.com.desafiovotacao.entity.dto.SessaoVotacaoDTO;
import br.com.desafiovotacao.service.SessaoVotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/sessoes")
@RequiredArgsConstructor
public class SessaoVotacaoController {
	
	private final SessaoVotacaoService service;
	
	@Operation(summary = "Abrir sessão", description = "Registra uma nova sessão para a pauta")
	@PostMapping
	public ResponseEntity<SessaoVotacaoDTO> criar(@RequestBody @Valid AbrirSessaoRequest payload) {
		SessaoVotacaoDTO criada = service.criar(payload);
		return ResponseEntity.status(HttpStatus.CREATED).body(criada);
	}
	
	@Operation(summary = "Listar sessões", description = "Lista as sessões ativas/inativas para uma pauta")
	@GetMapping
	public ResponseEntity<List<SessaoVotacaoDTO>> listar(@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = true) UUID pautaId,
			@RequestParam(required = false) Boolean ativa) {
		return ResponseEntity.ok(service.listar(offset, size, pautaId, ativa));
	}

}

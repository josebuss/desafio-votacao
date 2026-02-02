package br.com.desafiovotacao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiovotacao.entity.dto.PautaDTO;
import br.com.desafiovotacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

	private final PautaService service;

	@Operation(summary = "Criar pauta", description = "Registra uma nova pauta")
	@PostMapping
	public ResponseEntity<PautaDTO> criar(@RequestBody @Valid PautaDTO payload) {
		PautaDTO criada = service.criar(payload);
		return ResponseEntity.status(HttpStatus.CREATED).body(criada);
	}

	@Operation(summary = "Listar pautas", description = "Lista as pautas registradas")
	@GetMapping
	public ResponseEntity<List<PautaDTO>> listar(@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String codigo,
			@RequestParam(required = false) String descricao) {
		return ResponseEntity.ok(service.listar(offset, size, codigo, descricao));
	}

}

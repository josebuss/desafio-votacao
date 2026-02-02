package br.com.desafiovotacao.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AbrirSessaoRequest( //
		@NotNull UUID pautaId, //
		@Min(1) Long duracao) {
}

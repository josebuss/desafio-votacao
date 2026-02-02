package br.com.desafiovotacao.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VotoRequest( //
		@NotNull UUID sessaoId, //
		@Size(min = 11, max = 11, message = "deve conter 11 dígitos") @NotNull String cpf, //
		@NotNull Boolean aprova) {
}

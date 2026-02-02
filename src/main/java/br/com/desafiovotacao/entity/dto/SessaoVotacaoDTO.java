package br.com.desafiovotacao.entity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoVotacaoDTO {

	private UUID id;
	private PautaDTO pauta;
	private LocalDateTime inicio;
	private LocalDateTime fim;

	private LocalDateTime datger;
	private LocalDateTime datalt;
	
}

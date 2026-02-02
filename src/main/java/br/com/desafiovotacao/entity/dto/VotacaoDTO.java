package br.com.desafiovotacao.entity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotacaoDTO {
	
	private UUID id;
	private SessaoVotacaoDTO sessaoVotacao;
	private String cpf;
	private Boolean aprova;
	private LocalDateTime datger;
	private LocalDateTime datalt;

}

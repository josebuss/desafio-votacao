package br.com.desafiovotacao.entity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.desafiovotacao.entity.ResultadoVotacaoPautaEnum;
import br.com.desafiovotacao.entity.SessaoVotacaoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoVotacaoPautaDTO {
	
	private UUID id;
	private SessaoVotacaoEntity sessaoVotacao;
	private Integer totalSim;
	private Integer totalNao;
	private Integer totalVotos;
	private ResultadoVotacaoPautaEnum resultado;

	private LocalDateTime datger;
	private LocalDateTime datalt;

}

package br.com.desafiovotacao.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resultado_votacao_pauta")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ResultadoVotacaoPautaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "pauta_id", nullable = false, unique = true)
	private PautaEntity pauta;
	@ManyToOne(optional = false)
	@JoinColumn(name = "sessao_votacao_id", nullable = false, unique = true)
	private SessaoVotacaoEntity sessaoVotacao;

	@Column(name = "total_sim", nullable = false)
	private Integer totalSim;
	@Column(name = "total_nao", nullable = false)
	private Integer totalNao;
	@Column(name = "total_votos", nullable = false)
	private Integer totalVotos;
	@Enumerated(EnumType.STRING)
	@Column(name = "resultado", nullable = false, length = 10)
	private ResultadoVotacaoPautaEnum resultado;

	@CreatedDate
	@Column(name = "datger", nullable = false, updatable = false)
	private LocalDateTime datger;
	@LastModifiedDate
	@Column(name = "datalt")
	private LocalDateTime datalt;

}

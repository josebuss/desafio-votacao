package br.com.desafiovotacao.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sessao_votacao")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SessaoVotacaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pauta_id", nullable = false, unique = true)
	private PautaEntity pauta;

	@Column(name = "inicio", nullable = false)
	private LocalDateTime inicio;

	@Column(name = "fim", nullable = false)
	private LocalDateTime fim;

	@CreatedDate
	@Column(name = "datger", nullable = false, updatable = false)
	private LocalDateTime datger;
	@LastModifiedDate
	@Column(name = "datalt")
	private LocalDateTime datalt;

}

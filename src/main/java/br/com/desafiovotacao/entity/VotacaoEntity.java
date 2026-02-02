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
@Table(name = "votacao")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class VotacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "sessao_votacao_id", nullable = false, unique = true)
	private SessaoVotacaoEntity sessaoVotacao;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "aprova", nullable = false)
	private Boolean aprova;

	@CreatedDate
	@Column(name = "datger", nullable = false, updatable = false)
	private LocalDateTime datger;
	@LastModifiedDate
	@Column(name = "datalt")
	private LocalDateTime datalt;

}

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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pauta")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class PautaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, unique = true, length = 50)
	private String codigo;
	@Column(nullable = false, length = 255)
	private String descricao;
	
	@CreatedDate
	@Column(name = "datger", nullable = false, updatable = false)
	private LocalDateTime datger;
	@LastModifiedDate
	@Column(name = "datalt")
	private LocalDateTime datalt;

}

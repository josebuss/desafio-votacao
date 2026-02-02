package br.com.desafiovotacao.entity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO {
	
	private UUID id;
	@NotBlank
    private String codigo;
	@NotBlank
    private String descricao;
    private LocalDateTime datger;
    private LocalDateTime datalt;

}

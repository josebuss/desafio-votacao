package br.com.desafiovotacao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafiovotacao.entity.dto.ResultadoVotacaoPautaDTO;
import br.com.desafiovotacao.service.ResultadoVotacaoPautaService;

@ExtendWith(MockitoExtension.class)
class ResultadoVotacaoPautaControllerTest {
	
	private static final UUID PAUTA_ID = UUID.fromString("3fc7f99d-7e2d-4da4-8ca2-5b40a734b1d2");
	
	@InjectMocks
	private ResultadoVotacaoPautaController controller;
	@Mock
	private ResultadoVotacaoPautaService service;

	@Test
	void testSucessoListagem() {
		when(service.listar(0, 10, PAUTA_ID)).thenReturn(List.of(new ResultadoVotacaoPautaDTO(), new ResultadoVotacaoPautaDTO()));

		var output = controller.listar(0, 10, PAUTA_ID);

		assertNotNull(output);
		assertEquals(2, output.getBody().size());
		verify(service).listar(anyInt(), anyInt(), any(UUID.class));
		verifyNoMoreInteractions(service);
	}

}

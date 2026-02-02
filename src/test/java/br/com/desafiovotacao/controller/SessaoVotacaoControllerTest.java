package br.com.desafiovotacao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
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

import br.com.desafiovotacao.dto.AbrirSessaoRequest;
import br.com.desafiovotacao.entity.dto.SessaoVotacaoDTO;
import br.com.desafiovotacao.service.SessaoVotacaoService;

@ExtendWith(MockitoExtension.class)
class SessaoVotacaoControllerTest {

	private static final UUID PAUTA_ID = UUID.fromString("3fc7f99d-7e2d-4da4-8ca2-5b40a734b1d2");
	@InjectMocks
	private SessaoVotacaoController controller;
	@Mock
	private SessaoVotacaoService service;

	@Test
	void testSucessoCriacao() {
		var payload = new AbrirSessaoRequest(PAUTA_ID, 10L);
		
		when(service.criar(payload)).thenReturn(new SessaoVotacaoDTO());
		
		var output = controller.criar(payload);
		
		assertNotNull(output);
		verify(service).criar(any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testSucessoListagem() {
		when(service.listar(0, 10, PAUTA_ID, true)).thenReturn(List.of(new SessaoVotacaoDTO(), new SessaoVotacaoDTO()));
		
		var output = controller.listar(0, 10, PAUTA_ID, true);
		
		assertNotNull(output);
		assertEquals(2, output.getBody().size());
		verify(service).listar(anyInt(), anyInt(), any(UUID.class), anyBoolean());
		verifyNoMoreInteractions(service);
	}

}

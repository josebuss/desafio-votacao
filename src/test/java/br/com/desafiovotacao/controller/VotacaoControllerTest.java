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

import br.com.desafiovotacao.dto.VotoRequest;
import br.com.desafiovotacao.entity.dto.VotacaoDTO;
import br.com.desafiovotacao.service.VotacaoService;


@ExtendWith(MockitoExtension.class)
class VotacaoControllerTest {
	
	private static final UUID SESSAO_ID = UUID.fromString("3fc7f99d-7e2d-4da4-8ca2-5b40a734b1d2");

	@InjectMocks
	private VotacaoController controller;
	@Mock
	private VotacaoService service;

	@Test
	void testSucessoCriacao() {
		var payload = new VotoRequest(SESSAO_ID, "13106737000135", true);
		
		when(service.criar(payload)).thenReturn(new VotacaoDTO());
		
		var output = controller.criar(payload);
		
		assertNotNull(output);
		verify(service).criar(any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testSucessoListagem() {
		when(service.listar(0, 10, SESSAO_ID)).thenReturn(List.of(new VotacaoDTO(), new VotacaoDTO()));
		
		var output = controller.listar(0, 10, SESSAO_ID);
		
		assertNotNull(output);
		assertEquals(2, output.getBody().size());
		verify(service).listar(anyInt(), anyInt(), any(UUID.class));
		verifyNoMoreInteractions(service);
	}

}

package br.com.desafiovotacao.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafiovotacao.entity.dto.PautaDTO;
import br.com.desafiovotacao.service.PautaService;

@ExtendWith(MockitoExtension.class)
class PautaControllerTest {
	
	@InjectMocks
	private PautaController controller;
	@Mock
	private PautaService service;

	@Test
	void testSucessoCriacao() {
		var payload = new PautaDTO();
		payload.setCodigo("A1B2");
		var dto = new PautaDTO();
		dto.setCodigo("A1B2");
		
		when(service.criar(payload)).thenReturn(dto);
		
		var output = controller.criar(payload);
		
		assertNotNull(output);
		verify(service).criar(any());
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testSucessoListagem() {
		when(service.listar(0, 10, "1", "Pauta Teste")).thenReturn(List.of(new PautaDTO(), new PautaDTO()));
		
		var output = controller.listar(0, 10, "1", "Pauta Teste");
		
		assertNotNull(output);
		assertEquals(2, output.getBody().size());
		verify(service).listar(anyInt(), anyInt(), anyString(), anyString());
		verifyNoMoreInteractions(service);
	}

}

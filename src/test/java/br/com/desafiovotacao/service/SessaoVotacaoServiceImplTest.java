package br.com.desafiovotacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import br.com.desafiovotacao.DatabaseBaseTest;
import br.com.desafiovotacao.dto.AbrirSessaoRequest;
import br.com.desafiovotacao.exception.ServiceException;
import br.com.desafiovotacao.repository.SessaoVotacaoRepository;
import br.com.desafiovotacao.utils.MensagensConstants;
import jakarta.inject.Inject;

@Sql(scripts = "/db/testdata/SessaoVotacaoServiceImplTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SessaoVotacaoServiceImplTest extends DatabaseBaseTest {
	
	private static final String SESSAO_VOTACAO_ANTIGA_ID = "e88ef005-11ef-47a2-b7b4-c4766b8e4726";

	private static final UUID PAUTA_ID = UUID.fromString("476289f8-9fb3-45d2-91a1-7ef6a5904815");

	@Inject
	private SessaoVotacaoService sessaoVotacaoService;
	@Inject
	private SessaoVotacaoRepository sessaoVotacaoServiceRepository;

	@Test
	void testCriacao() {
		var payload = new AbrirSessaoRequest(PAUTA_ID, 10L);

		var output = sessaoVotacaoService.criar(payload);

		var optEntity = sessaoVotacaoServiceRepository.findById(output.getId());

		assertTrue(optEntity.isPresent());
		assertEquals(PAUTA_ID, optEntity.get().getPauta().getId());
		assertNotNull(optEntity.get().getInicio());
		assertNotNull(optEntity.get().getFim());
		assertNotNull(optEntity.get().getDatger());
		assertNotNull(optEntity.get().getDatalt());
		
		assertEquals(optEntity.get().getInicio().plusMinutes(10L), optEntity.get().getFim());
	}

	@Test
	void testCriacaoErroJaExisteSessaoAtiva() {
		var payload = new AbrirSessaoRequest(UUID.fromString("6f1c6bc9-c83f-48ab-8dd8-313ee9c036ca"), 10L);
		
		var ex = assertThrows(ServiceException.class, () -> sessaoVotacaoService.criar(payload));
		assertEquals(MensagensConstants.JA_EXISTE_UMA_SESSAO_DE_VOTACAO_EM_ANDAMENTO, ex.getMessage());
	}

	@Test
	void testCriacaoErroPautaNaoEncontrada() {
		var payload = new AbrirSessaoRequest(UUID.randomUUID(), 10L);
		
		var ex = assertThrows(ServiceException.class, () -> sessaoVotacaoService.criar(payload));
		assertEquals(MensagensConstants.NAO_FOI_POSSIVEL_ENCONTRAR_A_PAUTA, ex.getMessage());
	}

	@Test
	void testListagemFiltrarAtiva() {
		var sessaoAtual = sessaoVotacaoService.criar(new AbrirSessaoRequest(PAUTA_ID, 10L));
		
		
		var output = sessaoVotacaoService.listar(0, 10, PAUTA_ID, true);

		assertEquals(1, output.size());
		assertEquals(sessaoAtual.getId(), output.get(0).getId());
	}

	@Test
	void testListagemFiltrarInativa() {
		sessaoVotacaoService.criar(new AbrirSessaoRequest(PAUTA_ID, 10L));
		
		var output = sessaoVotacaoService.listar(0, 10, PAUTA_ID, false);

		assertEquals(1, output.size());
		assertEquals(UUID.fromString(SESSAO_VOTACAO_ANTIGA_ID), output.get(0).getId());
	}

	@Test
	void testListagemFiltrarAmbos() {
		var sessaoAtual = sessaoVotacaoService.criar(new AbrirSessaoRequest(PAUTA_ID, 10L));
		
		var output = sessaoVotacaoService.listar(0, 10, PAUTA_ID, null);

		assertEquals(2, output.size());
		assertEquals(UUID.fromString(SESSAO_VOTACAO_ANTIGA_ID), output.get(0).getId());
		assertEquals(sessaoAtual.getId(), output.get(1).getId());
	}

	@Test
	void testListagemPadinacao() {
		var sessaoAtual = sessaoVotacaoService.criar(new AbrirSessaoRequest(PAUTA_ID, 10L));
		
		var output = sessaoVotacaoService.listar(0, 1, PAUTA_ID, null);
		assertEquals(1, output.size());
		assertEquals(UUID.fromString(SESSAO_VOTACAO_ANTIGA_ID), output.get(0).getId());

		output = sessaoVotacaoService.listar(1, 1, PAUTA_ID, null);
		assertEquals(1, output.size());
		assertEquals(sessaoAtual.getId(), output.get(0).getId());

		output = sessaoVotacaoService.listar(0, 10, PAUTA_ID, null);
		assertEquals(2, output.size());
		assertEquals(UUID.fromString(SESSAO_VOTACAO_ANTIGA_ID), output.get(0).getId());
		assertEquals(sessaoAtual.getId(), output.get(1).getId());
	}

}

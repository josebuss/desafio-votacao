package br.com.desafiovotacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import br.com.desafiovotacao.DatabaseBaseTest;
import br.com.desafiovotacao.dto.VotoRequest;
import br.com.desafiovotacao.exception.ServiceException;
import br.com.desafiovotacao.repository.VotacaoRepository;
import br.com.desafiovotacao.utils.MensagensConstants;
import jakarta.inject.Inject;

@Sql(scripts = "/db/testdata/VotacaoServiceImplTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class VotacaoServiceImplTest extends DatabaseBaseTest {

	private static final UUID SESSAO_VOTACAO_ANTIGA_ID = UUID.fromString("e88ef005-11ef-47a2-b7b4-c4766b8e4726");
	private static final UUID SESSAO_VOTACAO_ATUAL_ID = UUID.fromString("22ff68b5-5896-42c0-997a-8408b02aa5ff");

	@Inject
	private VotacaoService votacaoService;
	@Inject
	private VotacaoRepository votacaoServiceRepository;

	@Test
	void testCriacao() {
		var payload = new VotoRequest(SESSAO_VOTACAO_ATUAL_ID, "90457204001", Boolean.TRUE);

		var output = votacaoService.criar(payload);

		var optEntity = votacaoServiceRepository.findById(output.getId());

		assertTrue(optEntity.isPresent());
		assertEquals(SESSAO_VOTACAO_ATUAL_ID, optEntity.get().getSessaoVotacao().getId());
		assertEquals(payload.cpf(), optEntity.get().getCpf());
		assertEquals(payload.aprova(), optEntity.get().getAprova());
		assertNotNull(optEntity.get().getDatger());
		assertNotNull(optEntity.get().getDatalt());
	}

	@Test
	void testCriacaoErroUsuarioJaVotou() {
		var payload = new VotoRequest(SESSAO_VOTACAO_ATUAL_ID, "04639313004", Boolean.TRUE);

		votacaoService.criar(payload);

		var ex = assertThrows(ServiceException.class, () -> votacaoService.criar(payload));
		assertEquals(MensagensConstants.O_USUARIO_JA_VOTOU_NESTA_SESSAO, ex.getMessage());
	}

	@Test
	void testCriacaoErroSessaoEncerrada() {
		var payload = new VotoRequest(SESSAO_VOTACAO_ANTIGA_ID, "04639313004", Boolean.TRUE);

		var ex = assertThrows(ServiceException.class, () -> votacaoService.criar(payload));
		assertEquals(MensagensConstants.TEMPO_DE_VOTACAO_JA_ENCERRADO, ex.getMessage());
	}

	@Test
	void testCriacaoErroSessaoNaoEncontrada() {
		var payload = new VotoRequest(UUID.randomUUID(), "90457204001", Boolean.TRUE);

		var ex = assertThrows(ServiceException.class, () -> votacaoService.criar(payload));
		assertEquals(MensagensConstants.SESSAO_DE_VOTACAO_NAO_ENCONTRADA, ex.getMessage());
	}

	@Test
	void testListagem() {
		votacaoService.criar(new VotoRequest(SESSAO_VOTACAO_ATUAL_ID, "04639313004", Boolean.TRUE));
		votacaoService.criar(new VotoRequest(SESSAO_VOTACAO_ATUAL_ID, "90457204001", Boolean.FALSE));
		votacaoService.criar(new VotoRequest(SESSAO_VOTACAO_ATUAL_ID, "10317872087", Boolean.TRUE));

		var output = votacaoService.listar(0, 10, SESSAO_VOTACAO_ATUAL_ID);
		assertEquals(3, output.size());
	}

}

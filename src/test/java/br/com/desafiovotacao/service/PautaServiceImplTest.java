package br.com.desafiovotacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import br.com.desafiovotacao.DatabaseBaseTest;
import br.com.desafiovotacao.entity.dto.PautaDTO;
import br.com.desafiovotacao.repository.PautaRepository;
import jakarta.inject.Inject;

@Sql(scripts = "/db/testdata/PautaServiceImplTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PautaServiceImplTest extends DatabaseBaseTest {

	@Inject
	private PautaService pautaService;
	@Inject
	private PautaRepository pautaRepository;

	@Test
	void testCriacao() {
		var payload = new PautaDTO();
		payload.setCodigo("A1B2");
		payload.setDescricao("Pauta teste 01");

		var output = pautaService.criar(payload);

		var optEntity = pautaRepository.findById(output.getId());

		assertTrue(optEntity.isPresent());
		assertEquals(payload.getCodigo(), optEntity.get().getCodigo());
		assertEquals(payload.getDescricao(), optEntity.get().getDescricao());
		assertNotNull(optEntity.get().getDatger());
		assertNotNull(optEntity.get().getDatalt());
	}

	@Test
	void testListagemFiltrarCodigo() {
		var output = pautaService.listar(0, 10, "10", null);

		assertEquals(2, output.size());
		assertEquals("10", output.get(0).getCodigo());
		assertEquals("110", output.get(1).getCodigo());
	}

	@Test
	void testListagemFiltrarDescricao() {
		var output = pautaService.listar(0, 10, null, "Sol");

		assertEquals(1, output.size());
		assertEquals("Instalação de painel Solar", output.get(0).getDescricao());
	}

	@Test
	void testListagemFiltrarCodigoDescricao() {
		var output = pautaService.listar(0, 10, "1", "Sol");

		assertEquals(1, output.size());
		assertEquals("1", output.get(0).getCodigo());
		assertEquals("Instalação de painel Solar", output.get(0).getDescricao());
	}

	@Test
	void testListagemPadinacao() {
		var output = pautaService.listar(0, 1, null, null);
		assertEquals(1, output.size());

		output = pautaService.listar(0, 10, null, null);
		assertEquals(3, output.size());
	}

}

package br.com.desafiovotacao.service;

import static br.com.desafiovotacao.entity.ResultadoVotacaoPautaEnum.APROVADO;
import static br.com.desafiovotacao.entity.ResultadoVotacaoPautaEnum.INCONCLUSIVO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import br.com.desafiovotacao.DatabaseBaseTest;
import br.com.desafiovotacao.exception.ServiceException;
import br.com.desafiovotacao.utils.MensagensConstants;
import jakarta.inject.Inject;

@Sql(scripts = "/db/testdata/ResultadoVotacaoPautaServiceImplTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ResultadoVotacaoPautaServiceImplTest extends DatabaseBaseTest {

	private static final UUID PAUTA_1_ID = UUID.fromString("476289f8-9fb3-45d2-91a1-7ef6a5904815");
	private static final UUID PAUTA_2_ID = UUID.fromString("72e17ba5-aab6-4d9b-8d90-41e3d9cf2af6");

	@Inject
	private ResultadoVotacaoPautaService service;

	@Test
	void testListagemResumoNaoComputado() {
		var output = service.listar(0, 10, PAUTA_1_ID);
		
		assertEquals(1, output.size());
		assertEquals(2, output.get(0).getTotalSim());
		assertEquals(1, output.get(0).getTotalNao());
		assertEquals(3, output.get(0).getTotalVotos());
		assertEquals(APROVADO, output.get(0).getResultado());
	}

	@Test
	void testListagemResumoComputado() {
		var output = service.listar(0, 10, PAUTA_2_ID);
		
		assertEquals(1, output.size());
		assertEquals(5, output.get(0).getTotalSim());
		assertEquals(5, output.get(0).getTotalNao());
		assertEquals(10, output.get(0).getTotalVotos());
		assertEquals(INCONCLUSIVO, output.get(0).getResultado());
	}

	@Test
	void testListagemResumoTodasPautas() {
		var output = service.listar(0, 10, null);
		
		assertEquals(2, output.size());
		assertEquals(2, output.get(0).getTotalSim());
		assertEquals(1, output.get(0).getTotalNao());
		assertEquals(3, output.get(0).getTotalVotos());
		assertEquals(APROVADO, output.get(0).getResultado());
		
		assertEquals(5, output.get(1).getTotalSim());
		assertEquals(5, output.get(1).getTotalNao());
		assertEquals(10, output.get(1).getTotalVotos());
		assertEquals(INCONCLUSIVO, output.get(1).getResultado());
	}
	
	@Test
	void testListagemErroSessaoNaoEncontrada() {
		var ex = assertThrows(ServiceException.class, () -> service.listar(0, 10, UUID.randomUUID()));
		assertEquals(MensagensConstants.NAO_FOI_ENCONTRADO_NENHUMA_SESSAO_DE_VOTACAO_PARA_A_PAUTA_INFORMADA, ex.getMessage());
	}

	@Test
	void testListagemErroSessaoEmAndamento() {
		var ex = assertThrows(ServiceException.class, () -> service.listar(0, 10, UUID.fromString("100e578b-d0ea-4bfb-95f1-5a05a023273c")));
		assertEquals(MensagensConstants.NAO_E_POSSIVEL_APRESENTAR_O_RESULTADO_POIS_A_PAUTA_AINDA_ESTA_EM_ANDAMENTO, ex.getMessage());
	}

}

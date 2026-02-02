package br.com.desafiovotacao.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MensagensConstants {
		
	public static final String NAO_FOI_POSSIVEL_ENCONTRAR_A_PAUTA = "Não foi possível encontrar a pauta";
	public static final String JA_EXISTE_UMA_SESSAO_DE_VOTACAO_EM_ANDAMENTO = "Não é possível cadastrar, pois já existe uma sessão de votação em andamento";
	
	public static final String SESSAO_DE_VOTACAO_NAO_ENCONTRADA = "Sessão de votação não encontrada";
	public static final String TEMPO_DE_VOTACAO_JA_ENCERRADO = "Tempo de votação encerrado";
	public static final String O_USUARIO_JA_VOTOU_NESTA_SESSAO = "O usuário já votou nesta sessão";
	
	public static final String NAO_E_POSSIVEL_APRESENTAR_O_RESULTADO_POIS_A_PAUTA_AINDA_ESTA_EM_ANDAMENTO = "Não é possível apresentar o resultado, pois a sessão de votação para a pauta ainda está em andamento";
	public static final String NAO_FOI_ENCONTRADO_NENHUMA_SESSAO_DE_VOTACAO_PARA_A_PAUTA_INFORMADA = "Não foi encontrada nenhuma sessão de votação para a pauta informada";

}

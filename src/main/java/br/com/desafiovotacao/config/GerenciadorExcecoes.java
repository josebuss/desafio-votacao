package br.com.desafiovotacao.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.desafiovotacao.dto.ErroResponse;
import br.com.desafiovotacao.exception.ServiceException;

@RestControllerAdvice
public class GerenciadorExcecoes {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErroResponse> tratarErroTratado(ServiceException ex) {
		return ResponseEntity.status(ex.getStatus()) //
				.body(new ErroResponse(getErroStatus(ex.getStatus()), ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResponse> tratarErroValidacao(MethodArgumentNotValidException ex) {
		String mensagem = ex.getBindingResult().getFieldErrors() //
				.stream() //
				.map(err -> err.getField() + ": " + err.getDefaultMessage()) //
				.findFirst() //
				.orElse("Erro de validação");

		return ResponseEntity.status(BAD_REQUEST)
				.body(new ErroResponse(getErroStatus(BAD_REQUEST), mensagem));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErroResponse> tratarParametroObrigatorio(MissingServletRequestParameterException ex) {
		return ResponseEntity.status(BAD_REQUEST)
				.body(new ErroResponse(getErroStatus(BAD_REQUEST),
						"Parâmetro obrigatório não informado: " + ex.getParameterName()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErroResponse> tratarBodyInvalido(HttpMessageNotReadableException ex) {
		String mensagem = "Corpo da requisição inválido";
		if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
			mensagem = "Valor inválido para o campo: " + ife.getPath().get(0).getFieldName();
		} else if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.MismatchedInputException mie) {
			mensagem = "Tipo de dado inválido no corpo da requisição";
		}
		return ResponseEntity.status(BAD_REQUEST)
				.body(new ErroResponse(getErroStatus(BAD_REQUEST), mensagem));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroResponse> tratarErroIntegridade(DataIntegrityViolationException ex) {
		String mensagem = "Violação de integridade de dados";
		if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException cve) {
			String constraint = cve.getConstraintName();
			if (constraint != null) {
				if (constraint.contains("uk_") || constraint.contains("unique")) {
					mensagem = "Registro já existente";
				} else if (constraint.contains("fk_")) {
					mensagem = "Registro relacionado não encontrado";
				} else if (constraint.contains("not_null")) {
					mensagem = "Campo obrigatório não informado";
				}
			}
		}
		return ResponseEntity.status(BAD_REQUEST)
				.body(new ErroResponse(getErroStatus(BAD_REQUEST), mensagem));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResponse> tratarFalha(Exception ex) {
		return ResponseEntity.status(INTERNAL_SERVER_ERROR) //
				.body(new ErroResponse(getErroStatus(INTERNAL_SERVER_ERROR), "Erro interno"));
	}

	private String getErroStatus(HttpStatus status) {
		return status.value() + " - " + status.name();
	}
}

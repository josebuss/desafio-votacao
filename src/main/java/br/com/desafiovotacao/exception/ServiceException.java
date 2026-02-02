package br.com.desafiovotacao.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	
	public ServiceException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
	
}

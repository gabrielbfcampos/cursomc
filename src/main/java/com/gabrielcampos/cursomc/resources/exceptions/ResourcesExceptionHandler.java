package com.gabrielcampos.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabrielcampos.cursomc.services.exceptions.DataIntegrityException;
import com.gabrielcampos.cursomc.services.exceptions.ObjectNotFoundException;

//Classe manipuladora de erro
@ControllerAdvice
public class ResourcesExceptionHandler {

	// Método para receber a exceção e as informações da requisição
	// É necessário colocar a anotação exceptionHandler passando o tipo de exceção
	// para falar que o método vai tratar
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		// Instancia do standardError
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

		// retorna o objeto responseentity com o status de not found e com o corpo da
		// mensagem que eu criei
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		// Instancia do standardError
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

		// retorna o objeto responseentity com o status de Bad Request e com o corpo da
		// mensagem que eu criei
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}

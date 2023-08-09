package com.ribbtec.smartwallet.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ribbtec.smartwallet.dto.DadosEmpresaDTO;

@RestControllerAdvice
public class TratadorDeErros {

	//	RECURSO NÃO ENCONTRADO
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static ResponseEntity<DadosEmpresaDTO> tratamentoErro404() {
		return ResponseEntity.notFound().build();
	}
	
	//	ERRO NOS DADOS PASSADOS PELO CLIENTE
	@SuppressWarnings("rawtypes")
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public static ResponseEntity tratamentoErro400(MethodArgumentNotValidException exception) {
		
		var erros = exception.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(dadosErroValidacao::new));
	}
	
	private record dadosErroValidacao(String campo, String mensagem) {
		public dadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}

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
	
	
	
//	@ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity tratarErro404() {
//        return ResponseEntity.notFound().build();
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
//        var erros = ex.getFieldErrors();
//        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
//    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity tratarErroBadCredentials() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
//    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity tratarErroAuthentication() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
//    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity tratarErroAcessoNegado() {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity tratarErro500(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
//    }

//    private record DadosErroValidacao(String campo, String mensagem) {
//        public DadosErroValidacao(FieldError erro) {
//            this(erro.getField(), erro.getDefaultMessage());
//        }
//    }
}

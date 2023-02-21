package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarErro404(){
            return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception){
        var erros = exception.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(dadosInvalidos::new).toList());
    }

    private record dadosInvalidos(String campo, String mensagem){
        public dadosInvalidos(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}

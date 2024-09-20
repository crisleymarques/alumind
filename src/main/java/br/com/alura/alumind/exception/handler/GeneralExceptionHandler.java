package br.com.alura.alumind.exception.handler;

import br.com.alura.alumind.exception.AlumindException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Crisley Marques
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public GeneralExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Error> errors = listOfErrors(e.getBindingResult());
        return handleExceptionInternal(e, errors, headers, BAD_REQUEST, request);
    }

    private List<Error> listOfErrors(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, getLocale());
            String developerMessage = fieldError.toString();
            errors.add(new Error(userMessage, developerMessage));
        }

        return errors;
    }

    @ExceptionHandler(AlumindException.class)
    public ResponseEntity<String> getAlumindException(AlumindException e) {
        String erro = e.getMessage();
        return ResponseEntity.status(e.getStatus()).body(erro);
    }

    @Getter
    @AllArgsConstructor
    public static class Error {
        private String userMessage;
        private String developerMessage;
    }
}

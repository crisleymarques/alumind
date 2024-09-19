package br.com.alura.alumind.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Crisley Marques
 */
@Getter
public class AlumindException extends RuntimeException {
    private String title;
    private HttpStatus status;

    public AlumindException() {}

    public AlumindException(String message) {
        super(message);
    }

    public AlumindException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlumindException(String title, String message) {
        super(message);
        this.title = message;
    }

    public AlumindException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public AlumindException(String title, String message, HttpStatus status) {
        super(message);
        this.title = message;
        this.status = status;
    }
}

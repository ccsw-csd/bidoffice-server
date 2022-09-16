package com.ccsw.bidoffice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que muestra un mensaje en función de si la fecha de creación de la
 * oferta está fuera de las fechas inicio-fin o si un registro tiene misma
 * prioridad o mismo nombre que otro previamente existente.
 * 
 * @author Francisco Sanz Pino.
 *
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class UpdateConflictException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message = "";

    /**
     * Constructor de excepción.
     * 
     * @param message Mensaje a mostrar.
     */
    public UpdateConflictException(String message) {

        super(message);

        this.message = message;
    }

    /**
     * Devuelve el mensaje de error.
     */
    public String getMessage() {

        return this.message;
    }

}

package com.ccsw.bidoffice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El registro tiene la misma prioridad o nombre que otro registro y no se puede guardar")
public class AlreadyExistsException extends Exception {

}
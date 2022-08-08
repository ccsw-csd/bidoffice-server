package com.ccsw.bidoffice.common.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists")
public class AlreadyExistsException extends Exception {

}
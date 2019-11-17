package com.tdg.mur.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class ThreadNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ThreadNotFoundException(String message) {
        super(message);
    }
}

package com.tdg.mur.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
        super(message);
    }
}

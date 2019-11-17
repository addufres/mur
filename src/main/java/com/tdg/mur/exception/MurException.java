package com.tdg.mur.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class MurException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MurException(String message) {
		super(message);
	}
}

package com.tdg.mur.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class MurException extends RuntimeException {
	public MurException(String message) {
		super(message);
	}
}

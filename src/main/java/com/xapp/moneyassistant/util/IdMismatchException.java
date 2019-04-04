package com.xapp.moneyassistant.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IdMismatchException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public IdMismatchException() {
		super();
	}

	public IdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdMismatchException(String message) {
		super(message);
	}

	public IdMismatchException(Throwable cause) {
		super(cause);
	}
	
}

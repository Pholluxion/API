package com.asdtest.exceptions;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "400 (Bad Request)";

	public BadRequestException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}

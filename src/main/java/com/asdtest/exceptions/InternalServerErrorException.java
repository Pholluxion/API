package com.asdtest.exceptions;

public class InternalServerErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "500 (Internal Server Error)";

	public InternalServerErrorException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}

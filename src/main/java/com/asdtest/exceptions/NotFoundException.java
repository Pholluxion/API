package com.asdtest.exceptions;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "404 (Not Found)";

	public NotFoundException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}

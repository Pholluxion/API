package com.asdtest.exceptions;

public class ErrorMessage {

	private String exeption;
	private String message;
	private String path;

	public ErrorMessage(Exception exeption, String path) {
		super();
		this.exeption = exeption.getClass().getSimpleName();
		this.message = exeption.getMessage();
		this.path = path;
	}

	public String getExeption() {
		return exeption;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ErrorMessage{" + "exeption:" + exeption + ",\n" + "message:" + message + ",\n" + "path:" + path + "}";
	}

}
